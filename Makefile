
JAVAC=/usr/bin/javac

.SUFFIXES: .java .class

SRCDIR=src
BINDIR=bin
DOC=./doc

$(BINDIR)/%.class:$(SRCDIR)/%.java
	$(JAVAC) -d $(BINDIR)/ -cp $(BINDIR) $<

CLASSES=Node.class Parallel.class SumArray.class TestParallel.class 

CLASSE=Node.java Parallel.java SumArray.java TestParallel.java
 
CLASS_FILES=$(CLASSES:%.class=$(BINDIR)/%.class)
SOURCEFILES=$(CLASSE:%.java=$(SRCDIR)/%.java)

default: $(CLASS_FILES)

clean:
	rm $(BINDIR)/*.class
run:
	java -cp $(BINDIR) TestParallel "input.txt" "output.txt"
	#java -cp $(BINDIR) TestSequential "file1.txt" "output11.txt"
	#java -cp $(BINDIR) TestSequential "file2.txt" "output12.txt"
	#java -cp $(BINDIR) TestSequential "file3.txt" "output13.txt"
	#java -cp $(BINDIR) TestSequential "file4.txt" "output14.txt"
	#java -cp $(BINDIR) TestSequential "file5.txt" "output15.txt"
	#java -cp $(BINDIR) TestSequential "file6.txt" "output16.txt"
	#java -cp $(BINDIR) TestSequential "file7.txt" "output17.txt"
	#java -cp $(BINDIR) TestSequential "file8.txt" "output18.txt"
	#java -cp $(BINDIR) TestSequential "file9.txt" "output19.txt"
	
run1:
	java -cp $(BINDIR) TestParallel "here.txt" "output2.txt"
	java -cp $(BINDIR) TestParallel  "file1.txt" "output21.txt"
	java -cp $(BINDIR) TestParallel  "file2.txt" "output22.txt"
	java -cp $(BINDIR) TestParallel  "file3.txt" "output23.txt"
	java -cp $(BINDIR) TestParallel  "file4.txt" "output24.txt"
	java -cp $(BINDIR) TestParallel  "file5.txt" "output25.txt"
	java -cp $(BINDIR) TestParallel  "file6.txt" "output26.txt"
	java -cp $(BINDIR) TestParallel  "file7.txt" "output27.txt"
	java -cp $(BINDIR) TestParallel  "file8.txt" "output28.txt"
	java -cp $(BINDIR) TestParallel  "file9.txt" "output29.txt"

doc:	$(CLASS_FILES)
	javadoc  -d $(DOC) $(SOURCEFILES)
