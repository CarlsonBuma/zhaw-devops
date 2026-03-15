# Task 1
Erstellung Gradle-Projekt zeigen, Verwendung .gitignore, Dependencies aus Vorlesung: Einbau, Installation und Verwendung zeigen

## Gradle
gradle init
gradle run
gradle build

## Initialisierung
Application (Typ)
Java (Implementation Language)
Single Application Project
Groovy (Build Script DSL)
JUnit Jupiter (Test Framework)
newAPIs: no

## Dependency 1 - Ascii Render
implementation 'com.indvd00m.ascii.render:ascii-render:2.2.0'

### Code
    IRender render = new Render();
    IContextBuilder builder = render.newBuilder();
    builder.width(120).height(20);
    builder.element(new PseudoText("PseudoText"));
    ICanvas canvas = render.render(builder.build());
    String s = canvas.getText();
    System.out.println(s);

## Dependency 2 - PDF Box
implementation 'org.apache.pdfbox:pdfbox:3.0.3'

### Code
    // PDF Box
    PDDocument helloPdf = new PDDocument();
    PDPage page = new PDPage(PDRectangle.A4);
    helloPdf.addPage(page);
    PDPageContentStream contentStream = new PDPageContentStream(helloPdf, page);
    contentStream.beginText();
    contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 36);
    contentStream.newLineAtOffset(5, 400);
    contentStream.showText("Hello DevOps!!!");
    contentStream.endText();
    contentStream.close();
    helloPdf.save(new File("simple.pdf"));
    helloPdf.close();

## Dependencies 3 - String‑Utilities
implementation 'org.apache.commons:commons-lang3:3.14.0'

### Code
    import org.apache.commons.lang3.StringUtils;

    public class DemoCommonsLang {
        public static void run() {
            String input = "hello world";
            String capitalized = StringUtils.capitalize(input);
            System.out.println("Capitalized: " + capitalized);
        }
    }

## Dependency 4 - Datei‑Utilities
implementation 'commons-io:commons-io:2.16.1'

### Code
    import org.apache.commons.io.FileUtils;
    import java.io.File;
    import java.nio.charset.StandardCharsets;

    public class DemoCommonsIO {
        public static void run() throws Exception {
            File file = new File("example.txt");

            // Datei schreiben
            FileUtils.writeStringToFile(file, "Hallo Gradle!", StandardCharsets.UTF_8);

            // Datei lesen
            String content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
            System.out.println("File content: " + content);
        }
    }

