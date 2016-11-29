import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/oi")
public class AdicionaContatoServlet extends HttpServlet {

	
	private static final long serialVersionUID = -5321877896051910352L;

	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String endereco = request.getParameter("endereco");
		String dataNascimento = request.getParameter("dataNascimento");

		Contato contato = new Contato();
		
		contato.setNome(nome);
		contato.setEmail(email);
		contato.setEndereco(endereco);
		
		Date dataConvertida = null;
		try {
			dataConvertida = new SimpleDateFormat("dd/MM/yyyy").parse(dataNascimento);
		} catch (ParseException e) {
			System.out.println("Não foi posspivel conveter a DATA!");	
		}
		contato.setDataNascimento(dataConvertida);
		
		ContatoDao dao = new ContatoDao();
		dao.inserirContato(contato);
		
		PrintWriter out =  response.getWriter();
		out.println("<html>");
        out.println("<body>");
        out.println("Contato " + contato.getNome() +
                " adicionado com sucesso");
        out.println("</body>");
        out.println("</html>");
	}
}
