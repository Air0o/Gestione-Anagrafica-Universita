package persistence;

import domain.Esame;
import domain.Studente;
import domain.Universita;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

/**
 *
 * @author GI.AIROLDI
 */
public class XMLParser {

    public Universita readFile() {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse("universita.xml");

            Element elementoUni = doc.getDocumentElement();
            elementoUni.normalize();

            String nomeUni = elementoUni.getAttribute("nome");
            Universita uni = new Universita(nomeUni);

            NodeList nlStudenti = elementoUni.getElementsByTagName("studente");

            for (int i = 0; i < nlStudenti.getLength(); i++) {
                Node nodeStudente = nlStudenti.item(i);

                if (!(nodeStudente instanceof Element)) {
                    continue;
                }

                Element elementStudente = (Element) nodeStudente;

                String nomeStudente = elementStudente.getElementsByTagName("nome").item(0).getTextContent();
                String cognomeStudente = elementStudente.getElementsByTagName("cognome").item(0).getTextContent();
                int etaStudente = Integer.parseInt(elementStudente.getAttribute("eta"));
                String corsoStudente = elementStudente.getAttribute("corso_di_laurea");

                Studente studente = new Studente(nomeStudente, cognomeStudente, etaStudente, corsoStudente);

                NodeList nlEsami = elementStudente.getElementsByTagName("esame");

                for (int j = 0; j < nlEsami.getLength(); j++) {
                    Node nodeEsame = nlEsami.item(j);

                    if (!(nodeEsame instanceof Element)) {
                        continue;
                    }

                    Element elementEsame = (Element) nodeEsame;

                    String nomeEsame = elementEsame.getAttribute("nome_esame");
                    int votoEsame = Integer.parseInt(elementEsame.getAttribute("voto"));
                    LocalDate dataEsame = LocalDate.parse(elementEsame.getElementsByTagName("data_esame").item(0).getTextContent());

                    Esame esame = new Esame(nomeEsame, votoEsame, dataEsame);

                    studente.aggiungiEsame(esame);
                }

                uni.aggiungiStudente(studente);
            }

            return uni;
        } catch (ParserConfigurationException ex) {
            System.getLogger(XMLParser.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (IOException ex) {
            System.getLogger(XMLParser.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (SAXException ex) {
            System.getLogger(XMLParser.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

        return null;
    }

    public void writeFile(Universita uni, String fileName) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();

            Element elementUni = doc.createElement("universita");

            elementUni.setAttribute("nome", uni.getNome());

            for (Studente studente : uni.getStudenti()) {
                Element elementStudente = doc.createElement("studente");

                elementStudente.setAttribute("corso_di_laurea", studente.getCorsoDiLaurea());
                elementStudente.setAttribute("eta", studente.getEta().toString());

                Element elementNomeStudente = doc.createElement("nome");
                Text textNomeStudente = doc.createTextNode(studente.getNome());
                elementNomeStudente.appendChild(textNomeStudente);
                elementStudente.appendChild(elementNomeStudente);

                Element elementCognomeStudente = doc.createElement("cognome");
                Text textCognomeStudente = doc.createTextNode(studente.getCognome());
                elementCognomeStudente.appendChild(textCognomeStudente);
                elementStudente.appendChild(elementCognomeStudente);

                Element elementEsami = doc.createElement("esami");
                for (Esame esame : studente.getEsami()) {
                    Element elementEsame = doc.createElement("esame");

                    elementEsame.setAttribute("nome_esame", esame.getNomeEsame());
                    elementEsame.setAttribute("voto", esame.getVoto().toString());

                    Element elementDataEsame = doc.createElement("data_esame");
                    Text textDataEsame = doc.createTextNode(esame.getDataEsame().toString());
                    elementDataEsame.appendChild(textDataEsame);
                    elementEsame.appendChild(elementDataEsame);

                    elementEsami.appendChild(elementEsame);
                }

                elementStudente.appendChild(elementEsami);
                elementUni.appendChild(elementStudente);
            }
            
            doc.appendChild(elementUni);

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource src = new DOMSource(doc);
            StreamResult res = new StreamResult(new File(fileName));

            transformer.transform(src, res);

        } catch (ParserConfigurationException ex) {
            System.getLogger(XMLParser.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (TransformerConfigurationException ex) {
            System.getLogger(XMLParser.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (TransformerException ex) {
            System.getLogger(XMLParser.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
}
