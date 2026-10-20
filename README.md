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

<img width="1801" height="926" alt="image7" src="https://github.com/user-attachments/assets/04120735-75e4-4a92-810d-5d08dc21d94a" />
<img width="931" height="1000" alt="image6" src="https://github.com/user-attachments/assets/567a8ef4-3972-402c-b621-ee93618ab342" />
<img width="897" height="870" alt="image5" src="https://github.com/user-attachments/assets/dbbb9255-c03d-48ee-afe8-4e984abff9c0" />
<img width="934" height="865" alt="image4" src="https://github.com/user-attachments/assets/4e182763-294e-474e-9525-fbab35203f65" />
<img width="375" height="832" alt="image3" src="https://github.com/user-attachments/assets/c66b3428-52d0-4a74-82eb-efa08a7808a1" />
<img width="441" height="613" alt="image2" src="https://github.com/user-attachments/assets/01e27175-0692-4979-99e9-855d3b1877c7" />
<img width="448" height="555" alt="image" src="https://github.com/user-attachments/assets/569525f1-e826-48fc-834b-105aca91aaa1" />

