-- Garante que a tabela Produto exista (opcional, mas boa prática)
-- Note: O Spring/Hibernate já criam a tabela, mas é bom garantir.
-- Caso você já tenha a tabela, comente esta linha
CREATE TABLE produto (id BIGINT PRIMARY KEY, nome VARCHAR(255), quantidade_em_estoque INTEGER); 

-- Inserção de dados de estoque para teste
-- ID 1: Estoque alto para simular sucesso
INSERT INTO produto (id, nome, quantidade_em_estoque) VALUES (1, 'Café Expresso', 50);

-- ID 2: Estoque médio
INSERT INTO produto (id, nome, quantidade_em_estoque) VALUES (2, 'Pão de Queijo', 10);

-- ID 3: Estoque baixo. Útil para testar a falha de estoque.
INSERT INTO produto (id, nome, quantidade_em_estoque) VALUES (3, 'Suco de Laranja', 5);