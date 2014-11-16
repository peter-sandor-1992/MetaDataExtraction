	Metadata Extractor application

1.DESCRIPTION
The presented application is an automatic header metadata extractor from scientific articles.
It uses a trained CRF classifier to label the header of scientific articles in PDF format.
The application can be used to train or test a CRF classifier, and it also has a GUI which provides
an easy interface for using the trained classifier to label article headers.

The application contains two projects:
	PDFExtact
	MetadataExtraction


2.CONFIGURATION
The application can be configured by setting the values in the MetadataExtraction/src/res/metadata_extraction.properties configuration file.


3.GETTING STARTED
The CD contains the compiled version of the software. The application can be run by simply double-clicking
on the compiled JAR file of the MetadataExctraction project, located in project/MetadataExtraction/dist/MetadataExtraction.jar
or by starting it in a cmd or terminal with the java -jar command.

To compile the projects, the PDFExtract/src and the MetadataExtraction/src folders must be compiled using a java compiler.

When running the application using the MetadataExtraction.jar file the following files are needed:
	- a trained CRF model, this file is loaded from the path given by the crfModel property's value in the configuration file.
		The default value for this property is model/crf_model, which means that the application will search for the trained CRF model
		in a folder named 'model' located in the same directory as the jar file, and the file's name must be 'crf_model'.
	- the lexicons used for feature generation, these must be placed in a directory called 'lexicons' which must be located in the same
		directory as the MetadataExtraction.jar file. The lexicon files can be found on the CD under project/MetadataExtraction/lexicons.
	- all the external libraries used by the application must be copied in a folder named 'lib' which must be placed in the same directory
		as the MetadataExtraction.jar file. These dependencies can be found on the CD under the project/MetadataExtraction/lib and
		project/PDFExtract/lib folders. The compiled jar file of the PDFExtract project must also be copied in this folder.
		

4.DOCUMENTATION
Documentation for both projects can be found on the CD, under the project/documentation folder.
