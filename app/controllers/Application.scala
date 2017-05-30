package controllers

import javax.inject.{Inject, Singleton}

import play.api.Logger
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.Json
import play.api.mvc._
import play.modules.reactivemongo.json.collection.JSONCollection
import play.modules.reactivemongo.{MongoController, ReactiveMongoApi, ReactiveMongoComponents}
import reactivemongo.api.collections.bson.BSONCollection
import reactivemongo.api.commands.bson.BSONCountCommand.{ Count, CountResult }
import reactivemongo.api.commands.bson.BSONCountCommandImplicits._
import reactivemongo.bson.BSONDocument

import scala.concurrent.Future

/**
  * Created by amadeusz on 14.05.2017.
  */
@Singleton
class Application @Inject()(val reactiveMongoApi: ReactiveMongoApi) extends Controller
  with MongoController with ReactiveMongoComponents {

  def jsonCollection = reactiveMongoApi.db.collection[JSONCollection]("surveys");
  def bsonCollection = reactiveMongoApi.db.collection[BSONCollection]("surveys");

  def index = Action {
    Logger.info("Index page visited...")
    Ok(views.html.Application.index("Witaj na stronie!"))
  }

  def index2 = Action {
    Logger.info("Index2 page visited...")
    Ok(views.html.Application.index2("Witaj!", singleton.Survey.questions))
  }

  def initDB = Action {
    Logger.info("DB init...")

    val posts = List(
      Json.obj(
        "qNumber" -> 1,
        "answer" -> "Tak",
        "dateTime" -> "05.05.2017 10:04:07"
      ),
      Json.obj(
        "qNumber" -> 2,
        "answer" -> "Nie",
        "dateTime" -> "05.05.2017 10:05:22"
      ),
      Json.obj(
        "qNumber" -> 3,
        "answer" -> "Zielony",
        "dateTime" -> "05.05.2017 10:06:12"
      ),
      Json.obj(
        "qNumber" -> 4,
        "answer" -> "Dużo",
        "dateTime" -> "05.05.2017 10:07:43"
      )
    )

    val query = BSONDocument("name" -> BSONDocument("$exists" -> true))
    val command = Count(query)
    val result: Future[CountResult] = bsonCollection.runCommand(command)

    result.map { res =>
      val numberOfDocs: Int = res.value
      if(numberOfDocs < 1) {
        jsonCollection.bulkInsert(posts.toStream, ordered = true).foreach(i => Logger.info("Record added."))
      }
    }

    Ok("Your database is ready.")
  }

  def cleanup = Action {
    jsonCollection.drop().onComplete {
      case _ => Logger.info("Database collection dropped")
    }
    Ok("Your database is clean.")
  }


}