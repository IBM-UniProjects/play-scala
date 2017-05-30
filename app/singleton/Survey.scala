package singleton

import scala.collection.immutable.ListMap
import scala.collection.immutable.List

/**
  * Created by amadeusz on 28.05.2017.
  */
object Survey {
  val questions: ListMap[String, Map[String, List[String]]] = ListMap(
    "age" -> Map(
      "question" -> List(
        "Wiek"
      ),
      "answers" -> List(
        "10 - 15",
        "15 - 20",
        "20 - 25",
        "25 - 30",
        "35 - 30",
        "więcej niż 35"
      )
    ),
    "gender" -> Map(
      "question" -> List(
        "Płeć"
      ),
      "answers" -> List(
        "kobieta",
        "mężczyzna"
      )
    ),
    "residence" -> Map(
      "question" -> List(
        "Miejsce zamieszkania"
      ),
      "answers" -> List(
        "wieś",
        "miasto do 10 tyś. mieszkańców",
        "miasto do 25 tyś. mieszkańców",
        "miasto do 100 tyś. mieszkańców",
        "miasto powyżej 100 tyś. mieszkańców"
      )
    ),
    "education" -> Map(
      "question" -> List(
        "Kierunek studiów"
      ),
      "answers" -> List(
        "humanistyczny",
        "ścisły",
        "nie studiuje"
      )
    ),
    "year" -> Map(
      "question" -> List(
        "Rok studiów"
      ),
      "answers" -> List(
        "1",
        "2",
        "3",
        "4",
        "5",
        "nie dotyczy"
      )
    ),
    "faith" -> Map(
      "question" -> List(
        "Stosunek do wiary"
      ),
      "answers" -> List(
        "jestem wierzący/a",
        "wierzę w siłę nadprzyrodzoną, ale wyznaję żadnej religii",
        "jestem agnostykiem/czką",
        "jestem ateistą/ką"
      )
    ),
    "family_faith" -> Map(
      "question" -> List(
        "Czy należysz do wierzącej rodziny?"
      ),
      "answers" -> List(
        "tak",
        "tylko jeden z rodziców",
        "nie"
      )
    ),
    "faith_importance" -> Map(
      "question" -> List(
        "Jak bardzo wiara jest ważna w Twoim życiu?"
      ),
      "answers" -> List(
        "nie ma dla mnie znaczenia",
        "ważna",
        "bardzo ważna"
      )
    ),
    "altruism" -> Map(
      "question" -> List(
        "Czy czujesz potrzebę niesienia pomocy innym?"
      ),
      "answers" -> List(
        "chciałbym, żeby efekty mojej pracy pomagały ludziom",
        "pomagam tylko ludziom w moim otoczeniu",
        "pomagam jeżeli ktoś poprosi mnie o pomoc",
        "nie czuję wewnętrznej potrzeby niesienia pomocy innym"
      )
    )
  )
}
