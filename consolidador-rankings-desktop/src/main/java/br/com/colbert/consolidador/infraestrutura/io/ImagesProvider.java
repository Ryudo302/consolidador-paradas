package br.com.colbert.consolidador.infraestrutura.io;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.swing.*;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;

/**
 * Classe que provê acesso a imagens.
 * 
 * @author Thiago Colbert
 * @since 18/12/2014
 */
@ApplicationScoped
public class ImagesProvider {

    private static final String IMAGES_DIR = "images";

    @Inject
    private Logger logger;

    public Icon chart() throws ImageLoadingException {
        return loadImageAsIcon("chart.png");
    }

    public Icon loading() throws ImageLoadingException {
        return loadImageAsIcon("carregando.gif");
    }

    private Image loadImage(String fileName) throws ImageLoadingException {
        return toBufferedImage(loadImageAsIcon(fileName));
    }

    public Icon loadImageAsIcon(String fileName) {
        logger.trace("Carregando imagem: {}", fileName);
        return new ImageIcon(FileUtils.toFile(Optional.ofNullable(Thread.currentThread().getContextClassLoader().getResource(IMAGES_DIR + '/' + fileName))
                .orElseThrow(() -> new ImageLoadingException("Arquivo inexistente: " + fileName))).getPath());
    }

    private BufferedImage toBufferedImage(Image imagem) {
        BufferedImage bufferedImage;

        if (imagem instanceof BufferedImage) {
            bufferedImage = (BufferedImage) imagem;
        } else if (imagem != null) {
            bufferedImage = new BufferedImage(imagem.getWidth(null), imagem.getHeight(null), BufferedImage.TYPE_INT_RGB);
            Graphics2D bufferedImageGraphics = bufferedImage.createGraphics();
            bufferedImageGraphics.drawImage(imagem, null, null);
        } else {
            bufferedImage = null;
        }

        return bufferedImage;
    }

    private BufferedImage toBufferedImage(Icon icon) {
        if (icon instanceof ImageIcon) {
            return toBufferedImage(((ImageIcon) icon).getImage());
        } else if (icon != null) {
            BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
            icon.paintIcon(new JCheckBox(), image.getGraphics(), 0, 0);
            return image;
        } else {
            return null;
        }
    }
}
