package ifce.edu.br.controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.imageio.ImageIO;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;


@ManagedBean // import javax.faces.bean.ManagedBean;
@ApplicationScoped
// @NoneScoped --> O bean será instanciado sempre que for referenciado
// @RequestScoped (padrão) --> Tempo de vida curto (Tempo de vida de uma requisição)
// @ViewScoped --> A instancia permanece ativa até que o usuário navegue para a próxima página
// @SessionScoped --> A instancia permanece ativa pelo período de uma sessão
// @ApplicationScoped --> A instância permanece ativa por todo o tempo de vida da aplicação.
public class CoresBean implements Serializable {

	private static final long serialVersionUID = 1L;
	//import javax.servlet.http.Part;
	private Part uploadedFile;
		
	private String cor;
	private List<String> cores = new ArrayList<>();
	
	public void add() {
		this.cores.add(cor);
		}
	
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public List<String> getCores() {
		return cores;
	}
	public void setCores(List<String> cores) {
		this.cores = cores;
	}
	
	
	public Part getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(Part uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public void upImage() {
		byte[] bytes = null;
		try {
			InputStream is = uploadedFile.getInputStream();
			bytes = IOUtils.toByteArray(is);
			ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
		    BufferedImage bImage2 = ImageIO.read(bis);
		    ImageIO.write(bImage2, "jpeg", new File("/home/corneli/eclipse-workspace-java/HelloWorld/WebContent/resources/images/imagem.jpeg") );
		    System.out.println("image created");
		    
			
		} catch (IOException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
