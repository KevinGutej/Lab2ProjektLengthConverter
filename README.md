## Wnioski i odpowiedzi na pytania do sprawozdania

### 1. Zachowanie blokady orientacji na telefonie i tablecie
W pliku `AndroidManifest.xml` zdefiniowano wymuszenie orientacji pionowej za pomocą atrybutu `android:screenOrientation="portrait"`.
* **Telefon:** Blokada działa prawidłowo. Podczas obracania urządzenia interfejs pozostaje sztywno w pozycji pionowej.
* **Tablet (Pixel Tablet):** System ignoruje blokadę podaną w manifeście – aplikacja płynnie przechodzi do trybu poziomego po obróceniu urządzenia.

**Wyjaśnienie różnicy:**
W nowszych wersjach systemu Android (od wersji 12/13/14) wprowadzono mechanizm ignorowania sztywnej blokady orientacji z manifestu na urządzeniach z dużym ekranem (najkrótsza krawędź $\ge 600\text{ dp}$, np. tablety czy telefony składane). Ma to na celu wymuszenie elastyczności aplikacji i zapobieganie sztucznemu ograniczaniu przestrzeni roboczej użytkownika.

---

### 2. Co powinno zastąpić blokowanie orientacji?
Zamiast blokowania orientacji na poziomie manifestu, nowoczesne aplikacje na Androida powinny wykorzystywać **elastyczny/responsywny interfejs (Responsive / Adaptive UI)**:
* **Adaptacyjny układ (Adaptive Layout):** Tworzenie widoków, które dynamicznie dostosowują rozkład elementów do dostępnej szerokości ekranu (np. układy dwukolumnowe w trybie poziomym lub na tabletach).
* **Klasy rozmiaru okna (Window Size Classes):** Programistyczne wykrywanie rozmiaru ekranu i dopasowywanie układu komponentów Compose.
* **Prawidłowe zarządzanie stanem:** Użycie `rememberSaveable`, co gwarantuje zachowanie wprowadzonych danych przy zmianie konfiguracji (obrót ekranu, zmiana rozmiaru okna).

---

### 3. Uzasadnienie przyjętego sposobu formatowania wyników
W aplikacji zastosowano dedykowaną funkcję `formatResult`, która dobiera sposób wyświetlania liczby zależnie od jej rzędu wielkości:
* **Dla standardowych wartości:** Stosowany jest czytelny format dziesiętny z przecinkiem jako separatorem (np. `1 500` czy `0,9321`), eliminujący zbędne zera na końcu.
* **Dla skrajnie małych wartości (np. lata świetlne):** Automatycznie aktywowana jest notacja naukowa/wykładnicza (np. `1.586e-13`).

**Uzasadnienie:**
Przeliczanie metrycznych jednostek długości na lata świetlne generuje wartości z bardzo dużą liczbą zer po przecinku. Standardowy zapis dziesiętny byłby nieczytelny i ucinałby istotne cyfry znaczące. Zastosowanie notacji naukowej dla bardzo małych wartości pozwala na zachowanie precyzji matematycznej oraz przejrzystości interfejsu.
