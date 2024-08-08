// FactoryMethodPatternExample.java
public class FactoryMethodPatternExample {

    // Document interface
    public interface Document {
        void open();
        void close();
    }

    // Concrete Document Classes
    public static class WordDocument implements Document {
        @Override
        public void open() {
            System.out.println("Opening Word Document.");
        }

        @Override
        public void close() {
            System.out.println("Closing Word Document.");
        }
    }

    public static class PdfDocument implements Document {
        @Override
        public void open() {
            System.out.println("Opening PDF Document.");
        }

        @Override
        public void close() {
            System.out.println("Closing PDF Document.");
        }
    }

    public static class ExcelDocument implements Document {
        @Override
        public void open() {
            System.out.println("Opening Excel Document.");
        }

        @Override
        public void close() {
            System.out.println("Closing Excel Document.");
        }
    }

    // Document Factory
    public abstract static class DocumentFactory {
        public abstract Document createDocument();
    }

    // Concrete Factory Classes
    public static class WordDocumentFactory extends DocumentFactory {
        @Override
        public Document createDocument() {
            return new WordDocument();
        }
    }

    public static class PdfDocumentFactory extends DocumentFactory {
        @Override
        public Document createDocument() {
            return new PdfDocument();
        }
    }

    public static class ExcelDocumentFactory extends DocumentFactory {
        @Override
        public Document createDocument() {
            return new ExcelDocument();
        }
    }

    // Test the Factory Method Implementation
    public static void main(String[] args) {
        DocumentFactory wordFactory = new WordDocumentFactory();
        Document wordDocument = wordFactory.createDocument();
        wordDocument.open();
        wordDocument.close();

        DocumentFactory pdfFactory = new PdfDocumentFactory();
        Document pdfDocument = pdfFactory.createDocument();
        pdfDocument.open();
        pdfDocument.close();

        DocumentFactory excelFactory = new ExcelDocumentFactory();
        Document excelDocument = excelFactory.createDocument();
        excelDocument.open();
        excelDocument.close();
    }
}
