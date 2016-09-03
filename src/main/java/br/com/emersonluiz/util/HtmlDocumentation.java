package br.com.emersonluiz.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.emersonluiz.model.Erd;
import br.com.emersonluiz.model.Entities;
import br.com.emersonluiz.model.Json;
import br.com.emersonluiz.model.JsonProperty;
import br.com.emersonluiz.model.Rest;
import br.com.emersonluiz.model.System;

public class HtmlDocumentation {

    DocumentFile documentFile;
    String dirBase;
    private static final Logger logger = LoggerFactory.getLogger(HtmlDocumentation.class);

    public HtmlDocumentation(String folderBase) throws Exception {
        documentFile = new DocumentFile();
        this.dirBase = documentFile.createFolderDocumentation(folderBase);
        createCSS(dirBase);
    }

    public void json(Json json, List<JsonProperty> properties) throws Exception {
        try {
            documentFile.createFolderDocumentation(dirBase, "json");
            documentFile.createFileDocumentation(dirBase + File.separator + "json", json.getName() + ".html");

            File file = new File(dirBase + File.separator + "json" + File.separator + json.getName() + ".html");

            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw = header(bw, "JSON - " + json.getName(), "../style.css");

            bw.newLine();
            bw.write("<nav class='bar'><a href='../index.html'>Design</a> / <a href='../json.html'>JSON</a> / " + json.getName() + "</nav>");
            bw.newLine();
            bw.write("<h2>"+ json.getName() +"</h2>");
            bw.newLine();
            bw.write("<p>"+ json.getDescription() +"</p>");
            bw.write("<br />");
            bw.newLine();
            bw.write("<div class='code'>");
            bw.newLine();
            bw.write("<pre>");
            bw.newLine();
            bw.write("{");

            boolean start = false;
            for (JsonProperty p : properties) {
                if (start) {
                    bw.write(", ");
                }
                bw.newLine();
                if (p.getRelatedJson() != null) {
                    bw.write("<span class='jsonProperty'>\""+ p.getProperty() +"\"</span> : <span class='jsonValue'><a href='"+ p.getRelatedJson().getName() +".html'>" + p.getRelatedJson().getName() + "</a></span>");
                } else {
                    bw.write("<span class='jsonProperty'>\""+ p.getProperty() +"\"</span> : <span class='jsonValue'>\"{" + p.getType() + "}\"</span>");
                }
                start = true;
            }

            bw.newLine();
            bw.write("}");
            bw.newLine();
            bw.write("</pre>");
            bw.newLine();
            bw.write("</div>");
            bw.newLine();

            bw = footer(bw);

            bw.close();
            fw.close();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    public void listJson(List<Json> jsons) throws Exception {
        try {
            documentFile.createFileDocumentation(dirBase, "json.html");
            File file = new File(dirBase + File.separator + "json.html");

            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw = header(bw, "JSON documentation", "style.css");

            bw.newLine();
            bw.write("<nav class='bar'><a href='index.html'>Design</a> / JSON</nav>");
            bw.newLine();
            bw.write("<h2>JSON</h2>");
            bw.newLine();
            bw.write("<ul>");

            for (Json j: jsons) {
                bw.newLine();
                bw.write("<li><a href='json/" + j.getName() + ".html'>" + j.getName() + "</a></li>");
            }

            bw.newLine();
            bw.write("</ul>");
            bw = footer(bw);

            bw.close();
            fw.close();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    public void rest(Entities entity, List<Rest> rest) throws Exception {
        try {
            documentFile.createFolderDocumentation(dirBase, "rest");
            documentFile.createFileDocumentation(dirBase + File.separator + "rest", entity.getName() + ".html");

            File file = new File(dirBase + File.separator + "rest" + File.separator + entity.getName() + ".html");

            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw = header(bw, "Rest - " + entity.getName(), "../style.css");

            bw.newLine();
            bw.write("<nav class='bar'><a href='../index.html'>Design</a> / <a href='../rest.html'>Rest API</a> / " + entity.getName() + "</nav>");
            bw.newLine();
            bw.write("<h2>"+ entity.getName() +"</h2>");
            bw.newLine();
            bw.write("<p>"+ entity.getDescription() +"</p>");
            bw.newLine();
            bw.write("<table class='rest'>");
            bw.newLine();
            bw.write("<tr>");
            bw.newLine();
            bw.write("<th>Action</th>");
            bw.newLine();
            bw.write("<th>Description</th>");
            bw.newLine();
            bw.write("<th>HTTP Method</th>");
            bw.newLine();
            bw.write("<th>Resource</th>");
            bw.newLine();
            bw.write("<th>Request JSON</th>");
            bw.newLine();
            bw.write("<th>Response JSON</th>");
            bw.newLine();
            bw.write("</tr>");

            for (Rest r : rest) {
                bw.newLine();
                bw.write("<tr>");
                bw.newLine();
                bw.write("<td>"+r.getAction()+"</td>");
                bw.newLine();
                bw.write("<td>"+r.getDescription()+"</td>");
                bw.newLine();
                bw.write("<td>"+r.getMethod()+"</td>");
                bw.newLine();
                bw.write("<td>"+r.getResource()+"</td>");
                bw.newLine();
                if (r.getRequestJson() != null) {
                    bw.write("<td><a href='../json/" + r.getRequestJson().getName() + ".html'>" + r.getRequestJson().getName() + "</a></td>");
                } else {
                    bw.write("<td></td>");
                }
                bw.newLine();
                if (r.getResponseJson() != null) {
                    bw.write("<td><a href='../json/" + r.getResponseJson().getName() + ".html'>" + r.getResponseJson().getName() + "</a></td>");
                } else {
                    bw.write("<td></td>");
                }
                bw.newLine();
                bw.write("</tr>");
            }

            bw.newLine();
            bw.write("</table>");
            bw = footer(bw);

            bw.close();
            fw.close();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    public void listRest(List<Entities> entities) throws Exception {
        try {
            documentFile.createFileDocumentation(dirBase, "rest.html");
            File file = new File(dirBase + File.separator + "rest.html");

            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw = header(bw, "Rest API documentation", "style.css");

            bw.newLine();
            bw.write("<nav class='bar'><a href='index.html'>Design</a> / Rest API</nav>");
            bw.newLine();
            bw.write("<h2>Rest API</h2>");
            bw.newLine();
            bw.write("<ul>");

            for (Entities e: entities) {
                bw.newLine();
                bw.write("<li><a href='rest/" + e.getName() + ".html'>" + e.getName() + "</a></li>");
            }

            bw.newLine();
            bw.write("</ul>");
            bw = footer(bw);

            bw.close();
            fw.close();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    public void listErd(List<Entities> entities) throws Exception {
        try {
            documentFile.createFileDocumentation(dirBase, "erd.html");

            File file = new File(dirBase + File.separator + "erd.html");
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw = header(bw, "ERD documentation", "style.css");

            bw.newLine();
            bw.write("<nav class='bar'><a href='index.html'>Design</a> / ERD</nav>");
            bw.newLine();
            bw.write("<h2>ERD</h2>");
            bw.newLine();
            bw.write("<ul>");

            for (Entities e: entities) {
                bw.newLine();
                bw.write("<li><a href='erd/" + e.getName() + ".html'>" + e.getName() + "</a></li>");
            }

            bw.newLine();
            bw.write("</ul>");
            bw = footer(bw);

            bw.close();
            fw.close();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    public void erd(Entities entity, Erd erd) throws Exception {
        try {
            documentFile.copy("system/" + entity.getSystem().getId() + "/entity/" + entity.getId() + "/erd/" + erd.getId(), dirBase, erd.getFileName());

            documentFile.createFolderDocumentation(dirBase, "erd");
            documentFile.createFileDocumentation(dirBase + File.separator + "erd", entity.getName() + ".html");

            File file = new File(dirBase + File.separator + "erd" + File.separator + entity.getName() + ".html");

            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw = header(bw, "ERD - " + entity.getName(), "../style.css");

            bw.newLine();
            bw.write("<nav class='bar'><a href='../index.html'>Design</a> / <a href='../erd.html'>ERD</a> / " + entity.getName() + "</nav>");
            bw.newLine();
            bw.write("<h2>"+ entity.getName() +"</h2>");
            bw.newLine();
            bw.write("<p>"+ entity.getDescription() +"</p>");
            bw.newLine();
            bw.write("<br />");
            bw.write("<img src='../img/"+erd.getFileName()+"' style='width:100%;' />");
            bw.newLine();
            bw = footer(bw);

            bw.close();
            fw.close();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    public void index(System system) throws Exception {
        try {
            documentFile.createFileDocumentation(dirBase, "index.html");
            File file = new File(dirBase + File.separator + "index.html");

            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw = header(bw, system.getName() + " documentation", "style.css");

            bw.newLine();
            bw.write("<h2>"+system.getName()+"</h2>");
            bw.newLine();
            bw.write("<p>"+ system.getDescription() +"</p>");
            bw.write("<br />");
            bw.newLine();
            bw.write("<h3>Design</h1>");
            bw.newLine();
            bw.write("<ul>");
            bw.newLine();
            bw.write("<li><a href='erd.html'>ERD Document</a></li>");
            bw.write("<li><a href='json.html'>JSON Document</a></li>");
            bw.write("<li><a href='rest.html'>Rest API</a></li>");

            bw.newLine();
            bw.write("</ul>");
            bw = footer(bw);

            bw.close();
            fw.close();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    private BufferedWriter header(BufferedWriter bw, String title, String style) throws Exception {
        try {
            bw.write("<html>");
            bw.newLine();
            bw.write("<head>");
            bw.newLine();
            bw.write("<title>" + title + "</title>");
            bw.newLine();
            bw.write("<meta charset='utf-8'>");
            bw.newLine();
            bw.write("<link rel='stylesheet' href='"+style+"' type='text/css'>");
            bw.newLine();
            bw.write("</head>");
            bw.newLine();
            bw.write("<body>");
            bw.newLine();
            bw.write("<section>");
            bw.newLine();
            bw.write("<article class='limite'>");
            return bw;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    private BufferedWriter footer(BufferedWriter bw) throws Exception {
        try {
            bw.write("</article>");
            bw.newLine();
            bw.write("</section>");
            bw.newLine();
            bw.write("</body>");
            bw.newLine();
            bw.write("</html>");
            return bw;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    private void createCSS(String directory) throws Exception {
        try {
            documentFile.createFileDocumentation(directory, "style.css");
            File file = new File(directory + File.separator + "style.css");

            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("*{margin:0 auto;padding:0}body{color:#333;background-color:rgb(92%,91%,88%)}h2{margin:1em 0}h3{margin:.5em 0}ul{margin:15px}a{color:#08c;text-decoration:none}a:focus,a:hover{color:#005580;text-decoration:underline}section{width:100%}pre{display:block;white-space:pre-line;margin:1em}ul#requirements li{margin:10px 0 10px -10px;list-style-type:none}ul#requirements li:before{content:'• ';color:#00f}table.rest{margin:1em 0;text-align:left;border:1px solid #CCC;border-collapse:collapse;border-spacing:0}table.rest td{border:1px solid #CCC;padding:.1em .25em}table.rest th{border:1px solid #BBB;padding:.1em .25em;background-color:#F7F7F7}.limite{width:980px;background:#fff;padding:15px}.code{border:1px solid #CCC;background:#F9F9F9}.bar{border:1px solid #DDD;background:#G9G9G9;padding:5px}.jsonProperty{margin-left:1em;color:navy}.jsonValue{color:#b84}.jsonValueObject{color:#3CB371}#diagram{width:960px}.h3Screen{margin-left:15px;color:#00f}");
            bw.close();
            fw.close();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }
}
