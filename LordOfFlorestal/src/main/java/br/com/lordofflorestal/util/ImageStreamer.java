
import br.com.lordofflorestal.model.Carta;
import br.com.lordofflorestal.model.Jogador;
import br.com.lordofflorestal.rn.CartaRN;
import br.com.lordofflorestal.rn.JogadorRN;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean
@ApplicationScoped
public class ImageStreamer implements Serializable {

    public StreamedContent getImagemCarta() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        } else {
            CartaRN cartaRN = new CartaRN();
            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
            String idCarta = context.getExternalContext().getRequestParameterMap().get("idCarta");
            Carta carta = cartaRN.buscarPorId(Integer.valueOf(idCarta));
            return new DefaultStreamedContent(new ByteArrayInputStream(carta.getImagem()));
        }
    }
    
    public StreamedContent getImagemJogador() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        } else {
            JogadorRN jogadorRN = new JogadorRN();
            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
            String idJogador = context.getExternalContext().getRequestParameterMap().get("idJogador");
            Jogador jogador = jogadorRN.buscarPorMatricula(Integer.valueOf(idJogador));
            return new DefaultStreamedContent(new ByteArrayInputStream(jogador.getImagem()));
        }
    }

}
