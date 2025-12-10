import React, { useEffect, useState } from "react";
import api from "../../service/api";
import "./style.css";

function HomePage() {
  const [produtos, setProdutos] = useState([]);

  async function carregarProdutos() {
    try {
      const resposta = await api.get("/produtos");
      setProdutos(resposta.data);
    } catch (erro) {
      console.error("Erro ao carregar produtos:", erro);
    }
  }

  useEffect(() => {
    carregarProdutos();
  }, []);

  return (
    <div className="home-container">
      <h1 className="home-title">Ofertas de Produtos</h1>

      <div className="produtos-grid">

        {produtos.map((produto) => (
          <div className="produto-card" key={produto.id}>
            
            <img 
              src={produto.fotoUrl} 
              alt={produto.nome} 
              className="produto-img"
            />

            <h2>{produto.nome}</h2>

            <p><b>Preço:</b> R$ {produto.preco}</p>

            <p><b>Quantidade:</b> {produto.quantidade}</p>

          </div>
        ))}

      </div>
    </div>
  );
}

export default HomePage;
