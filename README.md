![enter image description here](https://upload.wikimedia.org/wikipedia/commons/thumb/1/1f/Logo_FH_Muenster_cmyk.svg/1024px-Logo_FH_Muenster_cmyk.svg.png)
# Höhere Programmierungskonzepte
Das Projekt findet im Rahmen des Moduls Höhere Programmierungskonzepte im 3. Semester statt.


## Ziele des Praktikums 
- Weitergehende Konzepte der nebenläufigen und verteilten Programmierung kennen. 
- Beurteilen können, wann und wie ein Algorithmus sich erfolgreich parallelisieren oder verteilen lässt und dies am Beispiel der Java Virtuellen Maschine  implementieren können.
- Weitere Vor- und Nachteile der Java Sprache kennenlernen. 
- Weitere Sprachen oder generative Ansätze gezielt einsetzen können. 
- Das Modulsystem vom JDK 1.9+ und die aktuellen CI/CD Buildprozesse mit Maven und Gradle beherrschen.  
- Sinnvollen JUnit Tests zu den ausprogrammierten Projekteilen schreiben.


## Inhalte
- Professionelles Build Management mit Maven und Gradle und CI/CD per GIT.
- Das Java Module-System seit dem JDK 1.9
- Parallele Java Threads und deren Synchronisierung.
- Die Java Generics des JDK 1.5
- Die Java Reflection API und das Einbinden anderer Sprachen in die JVM: von Java zu C/C++ per JNI. 
- static und default Interface Methoden des JDK 1.8.


## Beschreibung des Praktikums
- Viele "Multiple Object-oriented Problem Solver” Instanzen **Mops** werden entwickelt. Diese Instanzen werden in einer ”Mops eXtendable Runtime“ Engine **MXR** registriert und ausgeführt. 
- Immer mehr Solver werden entwicklet und MXR wird inkrementell Schritt für Schritt weiter ausgebaut. 
- Mops fungiert ähnlich wie ein Micro-Service in der Cloud, nur hier in einer MXR - Ablaufumgebung. Mops ist eine einfache generische Schnittstelle, die innerhalb einer solve-Methode zu gegebenem **Problem** und **Fakten** eine Lösung findet. Dei Schnittstelle ist mittels **Generics** formuliert. 
- Die Mops Solver lassen sich registrieren und wieder auffinden anhand des Problems und der Faktenlage. Ein **MXR.Plugin** nimmt die Registrierung aller seiner von ihm angebotenen Maps Solver in der **MXREngine** vor.
- Die MXR Solver übernehmen unter anderem die Addition und Multiplikation von Vektoren und Matrizen von beliebigem Grad. 
- Die Matrizenmultiplikation, als eine häufige numerische Operation, wird mit geeigneten parallelen Algorithmen beschleunigt. Der user kann auswählen ob die Berechnung parallel oder sequentiell durchgeführt werden soll. 
- Die Tests der Algorithmen werden mit einer Zeitmessung instrumentiert, und ein **Speed-up** Faktor wird als Funktion der Matrizengröße berechnet. 
- Das Projekt wird auf das JDK 1.9 Modulsystem umgestellt. 
- Eine Java Klasse wird geschrieben, deren eigentliche Implementierung per JNI in C/C++ erfolgt und als Bibliothek eingebunden wird.
- Ein Algorithmus der numerischen Mathematik wird in C entwicklt. Ziel ist es eine C/C++ Bibliothek per Java Native Interface (JNI) im  **MXR.math** Plugin Teilprojekt zur Verfugung zu stellen. 
- Mit Hilfe von gekapselten C Funktionszeigern – und einer darauf abgestimmten C++ Funktionsklasse, die im Wesentlichen der Java Schnittstelle entspricht –, lassen sich effiziente Methoden entwickeln, um beliebige C und Java Funktionen numerisch zu differenzieren.
- Einen numerischen Algorithmus wird verfeinert und iterativ in einer Schleife ausgeführt um eine gewünschte Genauigkeit zu erreichen. Die nummerische Berechnung wird gegen analytische Ergebnisse geeignet getestet. 



## Nutzungsrechte 
Dieses Produkt unterliegt den Lizenzbestimmungen der FH Münster.



