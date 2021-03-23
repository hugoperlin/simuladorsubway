package ifpr.pgua.eic.simuladorsubway.utils;


import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import ifpr.pgua.eic.simuladorsubway.daos.interfaces.IngredienteDAO;
import ifpr.pgua.eic.simuladorsubway.models.Ingrediente;
import ifpr.pgua.eic.simuladorsubway.repositories.interfaces.IngredienteRepository;

import java.io.IOException;
import java.sql.SQLException;

public class GeradorPdf {

    private IngredienteRepository ingredienteRepository;

    public GeradorPdf(IngredienteRepository ingredienteRepository){
        this.ingredienteRepository = ingredienteRepository;
    }


    //cria um pdf com uma tabela que conterá
    // os ingredientes cadastrados e iremos mostrar o resultados
    //em um pdf.
    //Recebemos como parâmetro uma string com o caminho para salvar
    //o arquivo.
    public void criaPdf(String arq) throws SQLException {
        try{

            //buscando a lista de ingredientes
            java.util.List<Ingrediente> lista = ingredienteRepository.lista();

            //cria o documento
            PdfWriter writer = new PdfWriter(arq);
            PdfDocument pdf  = new PdfDocument(writer);
            //este será objeto que conterá o documento final.
            Document document = new Document(pdf, PageSize.A4,false);

            //criando um parágrafo
            Paragraph paragrafo = new Paragraph("Isto é um parágrafo, que " +
                    "você pode utilizar para inserir " +
                    "texto no seu relatório.");

            //inserimos o parágrafo no documento
            document.add(paragrafo);

            //coloca um parágrafo de cabeçalho, com alinhamento centralizado
            Paragraph paragrafo2 = new Paragraph("Ingredientes");
            //alinha contéudo do parágrafo no centro da página
            paragrafo.setTextAlignment(TextAlignment.CENTER);
            //indica que o parágrafo é negrito
            paragrafo.setBold();
            //inclui o paragrafo no documento
            document.add(paragrafo2);

            //cria a tabela
            Table table = new Table(UnitValue.createPercentArray(new float[]{50, 30, 20}))
                    .useAllAvailableWidth();

            //utilizado para criar o cabeçalho da tabela
            String[] cabecalho = {"Nome","Descrição","Valor"};

            //percore o vetor colocando cada elemento dentro de uma célula
            for(String s:cabecalho){
                //cria uma célula ue irá conter o conteúdo
                Cell cell = new Cell();
                //o conteúdo é coloca em um parágrafo
                cell.add(new Paragraph(s));
                //ajusta a cor de fundo da célula
                cell.setBackgroundColor(ColorConstants.LIGHT_GRAY);
                //ajusta a linha de borda da célula
                //https://api.itextpdf.com/iText7/7.1.7/com/itextpdf/layout/borders/SolidBorder.html
                cell.setBorder(new SolidBorder(ColorConstants.BLACK,2));
                //inclui a célula como cabeçalho, que irá se repetir por todas páginas em que a tabela aparecer (caso a quantidade
                //de dados for muito grande e precise de várias páginas)
                table.addHeaderCell(cell);

            }

            //cria uma fonte
            //https://api.itextpdf.com/iText7/7.1.7/com/itextpdf/io/font/constants/StandardFonts.html
            PdfFont font = PdfFontFactory.createFont(StandardFonts.COURIER);
            //ajusta a fonte da tabela, será utilizada por todas as células
            table.setFont(font);
            //ajusta o tamanho da fonte
            table.setFontSize(12);
            //percorre a lista e inclui as células. Cada atributo da pessoa
            //vai em uma célula separada

            int cont=0;

            for(Ingrediente i:lista){

                Color c = cont%2==0?ColorConstants.WHITE:ColorConstants.LIGHT_GRAY;

                Cell nome = new Cell();
                nome.add(new Paragraph(i.getNome())).setBackgroundColor(c);

                Cell descricao = new Cell();
                descricao.add(new Paragraph(i.getDescricao())).setBackgroundColor(c);

                Cell valor = new Cell();
                valor.add(new Paragraph(""+i.getValor())).setBackgroundColor(c);


                table.addCell(nome);
                table.addCell(descricao);
                table.addCell(valor);
                cont+=1;

            }

            //adiciona a tabela ao documento
            document.add(table);

            document.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
