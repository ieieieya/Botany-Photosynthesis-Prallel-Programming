
JAVAC=/usr/bin/javac

.SUFFIXES: .java .class

SRCDIR=src
BINDIR=bin
DOC=./doc

$(BINDIR)/%.class:$(SRCDIR)/%.java
	$(JAVAC) -d $(BINDIR)/ -cp $(BINDIR) $<

CLASSES=Node.class Parallel.class SumArray.class TestParallel.class TestSequential.class

CLASSE=Node.java Parallel.java SumArray.java TestParallel.java TestSequential.java
 
CLASS_FILES=$(CLASSES:%.class=$(BINDIR)/%.class)
SOURCEFILES=$(CLASSE:%.java=$(SRCDIR)/%.java)

default: $(CLASS_FILES)

clean:
	rm $(BINDIR)/*.class
gen:	
	java -cp $(BINDIR) RandomDataGenerator "input01.txt"
#Run the sequential program 10 times to get the ten values and to find the average run time
seq:
	java -cp $(BINDIR) TestSequential "input.txt" "outputSequential.txt"
	java -cp $(BINDIR) TestSequential "input.txt" "outputSequential.txt"
	java -cp $(BINDIR) TestSequential "input.txt" "outputSequential.txt"
	java -cp $(BINDIR) TestSequential "input.txt" "outputSequential.txt"
	java -cp $(BINDIR) TestSequential "input.txt" "outputSequential.txt"
	java -cp $(BINDIR) TestSequential "input.txt" "outputSequential.txt"
	java -cp $(BINDIR) TestSequential "input.txt" "outputSequential.txt"
	java -cp $(BINDIR) TestSequential "input.txt" "outputSequential.txt"
	java -cp $(BINDIR) TestSequential "input.txt" "outputSequential.txt"
	java -cp $(BINDIR) TestSequential "input.txt" "outputSequential.txt"
	
par:
	java -cp $(BINDIR) TestParallel "input.txt" "outputParallel.txt"

doc:	$(CLASS_FILES)
	javadoc  -d $(DOC) $(SOURCEFILES)
