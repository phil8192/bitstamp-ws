all:
	rm -rf target
	mvn clean compile assembly:single
