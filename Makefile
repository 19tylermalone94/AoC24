all:
	javac -d out $(shell find . -name "*.java")

run:
	java -cp out day_$(DAY).Main

