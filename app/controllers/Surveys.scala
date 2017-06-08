package controllers

import javax.inject.Inject


import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.Json
import play.api.mvc._
import play.modules.reactivemongo.{MongoController, ReactiveMongoApi, ReactiveMongoComponents}
import reactivemongo.bson.{BSONObjectID, BSONDocument}
import repos.SurveyRepoImpl
import singleton.Survey

/**
  * Created by amadeusz on 14.05.2017.
  */
class Surveys @Inject()(val reactiveMongoApi: ReactiveMongoApi) extends Controller
  with MongoController with ReactiveMongoComponents {

  import controllers.SurveyFields._

  def index = Action.async { implicit request =>
    surveyRepo.find().map(surveys => Ok(Json.toJson(surveys)))

  }

  def create = Action.async(BodyParsers.parse.json) { implicit request =>
    var document = BSONDocument()
    Questions.keys.foreach(question =>
      document = document.add(question -> (request.body \ question).as[Int])
    )
    surveyRepo.save(document).map(result => Created)
  }

  def read(id: String) = Action.async { implicit request =>
    surveyRepo.select(BSONDocument(Id -> BSONObjectID(id))).map(survey => Ok(Json.toJson(survey)))
  }

  def update(id: String) = Action.async(BodyParsers.parse.json) { implicit request =>
    var document = BSONDocument()
    Questions.keys.foreach(question =>
      document = document.add(question -> (request.body \ question).as[Int])
    )
    surveyRepo.update(BSONDocument(Id -> BSONObjectID(id)),
      BSONDocument("$set" -> document))
      .map(result => Accepted)
  }

  def delete(id: String) = Action.async {
    surveyRepo.remove(BSONDocument(Id -> BSONObjectID(id)))
      .map(result => Accepted)
  }

  def surveyRepo = new SurveyRepoImpl(reactiveMongoApi)
}

object SurveyFields {
  val Id = "_id"
  val Questions = Survey.questions
}
