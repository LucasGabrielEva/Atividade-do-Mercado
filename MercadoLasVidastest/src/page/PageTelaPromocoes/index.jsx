import { Link } from "react-router-dom";
import "./style.css";

function PageTelaPromocoes() {
  return (
    <div className="promo-container">
      <div className="promo-card">

        <h1 className="promo-titulo">Promoções do Mercado Las Vida</h1>
        <p className="promo-subtitulo">
          Aproveite nossas ofertas especiais e fique por dentro das próximas surpresas!
        </p>

        {/* Bloco de avisos especiais */}
        <div className="aviso-box">
          <h3>🔔 Avisos Importantes</h3>
          <ul>
            <li>🔥 Semana do Cliente — Grandes descontos vêm aí!</li>
            <li>🎁 Em breve: lançamento do programa de fidelidade.</li>
            <li>🛒 Novos produtos promocionais chegando nas lojas.</li>
          </ul>
        </div>

        {/* Botões */}
        <div className="botoes-container">
          <Link to="/produtos-promocionais" className="botao">
            Ver Produtos Promocionais
          </Link>

          <Link to="/cadastro-produto" className="botao secundario">
            Cadastrar Novo Produto
          </Link>

          <Link to="/" className="botao voltar">
            Voltar ao Menu Principal
          </Link>
        </div>

      </div>
    </div>
  );
}

export default PageTelaPromocoes;
