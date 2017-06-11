package controllers

import javax.inject.{Inject, Singleton}

import controllers.SurveyFields.Id
import play.api.Logger
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json._
import play.api.mvc._
import play.modules.reactivemongo.json.collection._
import play.modules.reactivemongo.json._
import play.modules.reactivemongo.{MongoController, ReactiveMongoApi, ReactiveMongoComponents}
import reactivemongo.api.collections.bson.BSONCollection
import reactivemongo.api.commands.bson.BSONCountCommand.{Count, CountResult}
import reactivemongo.api.commands.bson.BSONCountCommandImplicits._
import reactivemongo.bson.{BSONDocument, BSONObjectID}
import repos.SurveyRepoImpl

import scala.collection.mutable.ListMap
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

/**
  * Created by amadeusz on 14.05.2017.
  */
@Singleton
class Application @Inject()(val reactiveMongoApi: ReactiveMongoApi) extends Controller
  with MongoController with ReactiveMongoComponents {

  def jsonCollection = reactiveMongoApi.db.collection[JSONCollection]("surveys");
  def bsonCollection = reactiveMongoApi.db.collection[BSONCollection]("surveys");

  def surveyRepo = new SurveyRepoImpl(reactiveMongoApi)

  case class Question(age: Int, gender: Int, residence: Int, education: Int, year: Int, faith: Int, family_faith: Int,
                     religion_importance: Int, religion_practice: Int, altruism: Int, morality: Int, lgbt: Int, abortion: Int)
  object Question {
    implicit val reader: Reads[Question] = Json.reads[Question]
  }

  def index = Action {
    Logger.info("Index page visited...")
    val id = "0" * 24
    val query = surveyRepo.select(BSONDocument(Id -> BSONObjectID(id))).map(surveys => Json.toJson(surveys))
    val result = Await.result(query, 3 seconds).as[JsValue]
    Ok(views.html.Application.index(singleton.Survey.message, singleton.Survey.questions))
  }

  def results = Action {
    Logger.info("Results page visited...")
    val query = surveyRepo.find().map(surveys => Json.toJson(surveys))
    val result = Await.result(query, 3  seconds).as[JsValue]
    var surveys : ListMap[String, Array[Int]] = ListMap()
    val surveysNum = result.as[JsArray].value.length
    if (result.isInstanceOf[JsArray]) {
      for((question, body) <- singleton.Survey.questions) {
        val answers = (result.as[JsArray] \\ question)
        var count : Array[Int] = Array.fill[Int](body("answers").length)(0)
        answers.foreach(a => count(a.as[Int]) += 1)
        surveys += question -> count
      }
    }
    Ok(views.html.Application.results("Wyniki", singleton.Survey.questions, surveys, surveysNum))
  }

  def charts = Action {
    Logger.info("Chart page visited")
    val query = surveyRepo.find().map(surveys => Json.toJson(surveys))
    val result = Await.result(query, 3  seconds).as[JsValue]
    var surveys : ListMap[String, Array[Int]] = ListMap()
    val surveysNum = result.as[JsArray].value.length
    if (result.isInstanceOf[JsArray]) {
      for((question, body) <- singleton.Survey.questions) {
        val answers = (result.as[JsArray] \\ question)
        var count : Array[Int] = Array.fill[Int](body("answers").length)(0)
        answers.foreach(a => count(a.as[Int]) += 1)
        surveys += question -> count
      }
    }
    Ok(views.html.Application.charts("Wykresy", singleton.Survey.questions, surveys, surveysNum))
  }

  def bayes = Action {
    Logger.info("Bayes page visited")
    Ok(views.html.Application.bayes("Naiwny klasyfikator bayesowski", singleton.Survey.questions))
  }

  def apriori = Action {
    Logger.info("Apriori page visited")
    Ok(views.html.Application.apriori("Algorytm Apriori", singleton.Survey.questions))
  }

  def initDB = Action {
    Logger.info("DB init...")

//    val posts = List(
//      Json.obj(
//        "qNumber" -> 1,
//        "answer" -> "Tak",
//        "dateTime" -> "05.05.2017 10:04:07"
//      ),
//      Json.obj(
//        "qNumber" -> 2,
//        "answer" -> "Nie",
//        "dateTime" -> "05.05.2017 10:05:22"
//      ),
//      Json.obj(
//        "qNumber" -> 3,
//        "answer" -> "Zielony",
//        "dateTime" -> "05.05.2017 10:06:12"
//      ),
//      Json.obj(
//        "qNumber" -> 4,
//        "answer" -> "Dużo",
//        "dateTime" -> "05.05.2017 10:07:43"
//      )
//    )
//
//    val query = BSONDocument("name" -> BSONDocument("$exists" -> true))
//    val command = Count(query)
//    val result: Future[CountResult] = bsonCollection.runCommand(command)
//
//    result.map { res =>
//      val numberOfDocs: Int = res.value
//      if(numberOfDocs < 1) {
//        jsonCollection.bulkInsert(posts.toStream, ordered = true).foreach(i => Logger.info("Record added."))
//      }
//    }

    Ok("Nope.")
  }

  def cleanup = Action {
//    jsonCollection.drop().onComplete {
//      case _ => Logger.info("Database collection dropped")
//    }
    Ok("Nice try :)")
  }


}