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
        "mniej niż 18",
        "18 - 23",
        "23 - 28",
        "28 - 33",
        "33 - 38",
        "38 - 43",
        "więcej niż 43"
      )
    ),
    "gender" -> Map(
      "question" -> List(
        "Płeć"
      ),
      "answers" -> List(
        "kobieta",
        "mężczyzna",
        "inna"
      )
    ),
    "residence" -> Map(
      "question" -> List(
        "Miejsce zamieszkania"
      ),
      "answers" -> List(
        "wieś",
        "miasto do 50 tyś. mieszkańców",
        "miasto do 100 tyś. mieszkańców",
        "miasto do 200 tyś. mieszkańców",
        "miasto powyżej 200 tyś. mieszkańców"
      )
    ),
    "education" -> Map(
      "question" -> List(
        "Kierunek aktualnych lub ukończonych studiów",
        "W przypadku kilku wybierz bliższe Twoim zainteresowaniom."
      ),
      "answers" -> List(
        "humanistyczne",
        "społeczne",
        "ścisłe i techniczne",
        "przyrodnicze",
        "medyczne i nauki o zdrowiu",
        "artystyczne",
        "rolnicze, leśne i weterynaryjne",
        "nie dotyczy / inne"
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
        "Stosunek do wiary w Siłę wyższą*",
        "*Bóg, bogowie lub inne mieszczące się w tej kategorii.",
        "W skali Dawkinsa: od 1 - silny teizm, do 7 - silny ateizm."
      ),
      "answers" -> List(
        "1 - bardzo silna wiara, pewność - 'Nie wierzę, wiem.'",
        "2 - wiara - 'Nie mam 100% pewności, ale silnie wierzę i żyję zgodnie z przekonaniem, że Siła wyższa istnieje.'",
        "3 - skłanianie się w kierunku wiary - 'Jestem bardzo niepewna, ale skłonna wierze w Siłę wyższą.'",
        "4 - całkowita obojętność, agnostycyzm - 'Istnienie i nieistnienie Siły wyższej jest dla mnie równie prawdopodobne.'",
        "5 - skłanianie się w kierunku ateizmu - 'Jestem bardzo niepewna, ale skłonna myśli, że nie ma Siły wyższej.'",
        "6 - ateizm - 'Nie mam 100% pewności, ale koncepcja Siły wyższej jest dla mnie wysoce nieprawdopodobna i żyję zgodnie z przekonaniem, że jej nie ma.'",
        "7 - bardzo silny ateizm, pewność - 'Wiem, że nie istnieje.'"
      )
    ),
    "family_faith" -> Map(
      "question" -> List(
        "Czy należysz do wierzącej* rodziny?",
        "*Mniej niż 4 w skali Dawkinsa (pytanie 6)."
      ),
      "answers" -> List(
        "tak",
        "połowa jest wierząca",
        "nie"
      )
    ),
    "religion_importance" -> Map(
      "question" -> List(
        "Jak bardzo bardzo ważna w Twoim życiu jest religia*?",
        "*Zorganizowana religia, czynności religijne, nabożeństwa, inne mieszczące się w tej kategorii.",
        "W skali od 1 - silna awersja, do 5 - silne przywiązanie."
      ),
      "answers" -> List(
        "1 - odczuwam wrogość",
        "2 - odczuwam niechęć",
        "3 - jest mi obojętna",
        "4 - jest istotna w moim życiu",
        "5 - jest jedną z najważniejszych rzeczy w moim życiu"
      )
    ),
    "religion_practice" -> Map(
      "question" -> List(
        "Jak często uczęszczasz na nabożeństwa lub inne zorganizowane czynności religijne?"
      ),
      "answers" -> List(
        "nie uczęszczam",
        "kilka razy w roku",
        "co najmniej raz w miesiącu",
        "co najmniej raz w tygodniu"
      )
    ),
    "altruism" -> Map(
      "question" -> List(
        "Czy na codzień czujesz potrzebę niesienia pomocy innym?"
      ),
      "answers" -> List(
        "codziennie staram się bezinteresowanie pomagać innym",
        "pomagam głównie ludziom w moim otoczeniu",
        "pomagam jeżeli ktoś poprosi mnie o pomoc",
        "nie czuję wewnętrznej potrzeby niesienia pomocy innym"
      )
    ),
    "morality" -> Map(
      "question" -> List(
        "Czy uważasz, że istnieją absolutne zasady moralne?",
        "W skali od 1 - abosolutyzm moralny, do 5 - relatywizm moralny"
      ),
      "answers" -> List(
        "tak, potrafię je wskazać - 'Istnieją jedne słuszne zasady moralne, niezależne od człowieka i jego poglądów, których łamanie czyni osobę niemoralną.'",
        "prawdopodobnie tak, nie potrafię ich wskazać z pełną pewnością",
        "nie wiem / nie mam zdania",
        "prawdopodobnie nie, zasady moralne potrafią ewoluować",
        "nie, zasady moralne są tworem człowieka i różnią się w zależności od kultury"
      )
    ),
    "lgbt" -> Map(
      "question" -> List(
        "Jaki jest Twój stosunek do osób LGBT*?",
        "*Osoby homoseksulane, biseksualne, transpłciowe i inne mieszczące się w tej kategorii.",
        "W skali od 1 - silna awersja, do 5 - silna sympatia."
      ),
      "answers" -> List(
        "1 - odczuwam wrogość - 'Nie powinno ich być w społeczeństwie. Takie praktyki powinny być karane.'",
        "2 - odczuwam niechęć - 'Nie powinni się z tym obnosić publicznie. Sprzeciwiam się przychylnym im zmianom prawnym.'",
        "3 - całkowita obojętność",
        "4 - odczuwam sympatię - 'Powinni być lepiej traktowani w społeczeństwie. Skłaniam sie ku części postulatów tego środowiska.'",
        "5 - odczuwam silną sympatię - 'W pełni popieram postulaty tego środowiska i wszystkie proponowane zmiany prawne.'"
      )
    ),
    "abortion" -> Map(
      "question" -> List(
        "Jaki jest Twoje stanowisko w dykusji na temat aborcji?",
        "W skali od 1 - 'za życiem', do 5 - 'za wyborem'."
      ),
      "answers" -> List(
        "1 - stanowisko obozu Pro-life - 'Życie powinno być chronione od momentu poczęcia.'",
        "2 - zwiększenie ograniczeń - 'Dostęp do aborcji powinien zostać ograniczony, dopuszczalny tylko w najbardziej skrajnym przypadku, np. zagrożenia życia matki.'",
        "3 - status quo - 'Aktualny stan prawny jest odpowiedni.'",
        "4 - zmniejszenie ograniczeń - 'Obecny stan prawny jest zbyt rygorystyczny, prawo powinno nieznacznie zliberalizowane.'",
        "5 - stanowisko obozu Pro-choice - 'Kobiety powinny mieć prawo decydowania o swoim ciele.'"
      )
    )
  )
}
