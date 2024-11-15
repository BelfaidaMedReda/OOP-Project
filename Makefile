# Ensimag 2A POO 
# ============================
#
# Ce Makefile permet de compiler le test de l'ihm en ligne de commande.
# Alternative (recommandee?): utiliser un IDE (eclipse, netbeans, ...)
# Le but est ici d'illustrer les notions de "classpath", a vous de l'adapter
# a votre projet.
#
# Organisation:
#  1) Les sources (*.java) se trouvent dans le repertoire src
#     Les classes d'un package toto sont dans src/toto
#     Les classes du package par defaut sont dans src
#
#  2) Les bytecodes (*.class) se trouvent dans le repertoire bin
#     La hierarchie des sources (par package) est conservee.
#     L'archive lib/gui.jar contient les classes de l'interface graphique
#
# Compilation:
#  Options de javac:
#   -d : repertoire dans lequel sont places les .class compiles
#   -classpath : repertoire dans lequel sont cherches les .class deja compiles
#   -sourcepath : repertoire dans lequel sont cherches les .java (dependances)

all: testInvader testLecture Scenario1 Scenario2 TestDijkstra TestEvent TestSimulateur TestStrategy

testInvader:
	javac -d bin -classpath lib/gui.jar -sourcepath src src/TestInvader.java

testLecture:
	javac -d bin -sourcepath src src/TestLecteurDonnees.java

Scenario1:
	javac -d bin -classpath lib/gui.jar -sourcepath src src/Scenario1.java

Scenario2:
	javac -d bin -classpath lib/gui.jar -sourcepath src src/Scenario2.java

TestDijkstra:
	javac -d bin -classpath lib/gui.jar -sourcepath src src/TestDijkstra.java

TestEvent:
	javac -d bin -classpath lib/gui.jar -sourcepath src src/TestEvent.java

TestSimulateur:
	javac -d bin -classpath lib/gui.jar -sourcepath src src/TestSimulateur.java

TestStrategy:
	javac -d bin -classpath lib/gui.jar -sourcepath src src/TestStrategy.java

# Execution:
# on peut taper directement la ligne de commande :
#   > java -classpath bin:lib/gui.jar TestInvader
# ou bien lancer l'execution en passant par ce Makefile:
#   > make exeInvader
exeInvader: 
	java -classpath bin:lib/gui.jar TestInvader

exeLecture: 
	java -classpath bin TestLecteurDonnees cartes/carteSujet.map

exeScenario1:
	java -classpath bin:lib/gui.jar Scenario1

exeScenario2:
	java -classpath bin:lib/gui.jar Scenario2

exeTestDijkstra:
	java -classpath bin:lib/gui.jar TestDijkstra

exeTestEvent:
	java -classpath bin:lib/gui.jar TestEvent

exeTestSimulateur:
	java -classpath bin:lib/gui.jar TestSimulateur

exeTestStrategy:
	java -classpath bin:lib/gui.jar TestStrategy


clean:
	rm -rf bin/*
