package controllers

import javax.inject.Inject


import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.Json
import play.api.mvc._
import play.modules.reactivemongo.{MongoController, ReactiveMongoApi, ReactiveMongoComponents}
import reactivemongo.api.commands.WriteResult
import reactivemongo.bson.{BSONObjectID, BSONDocument}
import repos.WidgetRepoImpl

/**
  * Created by amadeusz on 14.05.2017.
  */
class Widgets @Inject()(val reactiveMongoApi: ReactiveMongoApi) extends Controller
  with MongoController with ReactiveMongoComponents {

  import controllers.WidgetFields._

  def index = Action.async { implicit request =>
    widgetRepo.find().map(widgets => Ok(Json.toJson(widgets)))
  }

  def create = Action.async(BodyParsers.parse.json) { implicit request =>
    val qNumber = (request.body \ QNumber).as[Int]
    val answer = (request.body \ Answer).as[String]
    val dateTime = (request.body \ DateTime).as[String]
    widgetRepo.save(BSONDocument(
      QNumber -> qNumber,
      Answer -> answer,
      DateTime -> dateTime
    )).map(result => Created)
  }

  def read(id: String) = Action.async { implicit request =>
    widgetRepo.select(BSONDocument(Id -> BSONObjectID(id))).map(widget => Ok(Json.toJson(widget)))
  }

  def update(id: String) = Action.async(BodyParsers.parse.json) { implicit request =>
    val qNumber = (request.body \ QNumber).as[String]
    val answer = (request.body \ Answer).as[String]
    val dateTime = (request.body \ DateTime).as[String]
    widgetRepo.update(BSONDocument(Id -> BSONObjectID(id)),
      BSONDocument("$set" -> BSONDocument(QNumber -> qNumber, Answer -> answer, DateTime -> dateTime)))
      .map(result => Accepted)
  }

  def delete(id: String) = Action.async {
    widgetRepo.remove(BSONDocument(Id -> BSONObjectID(id)))
      .map(result => Accepted)
  }

  def widgetRepo = new WidgetRepoImpl(reactiveMongoApi)
}

object WidgetFields {
  val Id = "_id"
  val QNumber = "qNumber"
  val Answer = "answer"
  val DateTime = "dateTime"
}
