import './style.css';

import { useEffect, useState } from "react";
import { toast } from "react-toastify";
import api from "../../service/api";

function PaginaListarProdutos() {
  // Estado para armazenar os produtos buscados na API
  const [produtos, setProdutos] = useState([]);

  // Estado para mostrar carregamento
  const [carregando, setCarregando] = useState(false);

  // Função responsável por buscar produtos na API
  async function carregarProdutos() {
    try {
      setCarregando(true);

      const resposta = await api.get("/produtos");

      setProdutos(resposta.data || []);


    } catch (erro) {
      console.error("Erro ao buscar produtos:", erro);

      const mensagemDoServidor =
        erro?.response?.data?.mensagem || "Não foi possível carregar os produtos.";

      toast.error(mensagemDoServidor);
    } finally {
      setCarregando(false);
    }
  }

  // Carrega produtos automaticamente quando a página abrir
  useEffect(() => {
    carregarProdutos();
  }, []);

  return (
    <div className="cadastro-container">
      <h1>Lista de Produtos</h1>

      <button className="btn-recarregar" onClick={carregarProdutos}>
        Atualizar Lista
      </button>

      {/* Exibe mensagem de carregamento */}
      {carregando && <p>Carregando produtos...</p>}

      {/* Caso não existam produtos */}
      {!carregando && produtos.length === 0 && (
        <p>Nenhum produto encontrado. Cadastre um novo!</p>
      )}

      {/* Tabela de produtos */}
      {produtos.length > 0 && (
        <table className="tabela-produtos">
          <thead>
            <tr>
              <th>Nome</th>
              <th>Preço (R$)</th>
              <th>Quantidade</th>
              <th>Descrição</th>
            </tr>
          </thead>

          <tbody>
            {produtos.map((produto) => (
              <tr key={produto.id}>
                <td>{produto.nome}</td>
                <td>{produto.preco.toFixed(2)}</td>
                <td>{produto.quantidade}</td>
                <td>{produto.descricao}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}

export default PaginaListarProdutos;
