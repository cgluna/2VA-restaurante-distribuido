## Descrição

Sistema de Gerenciamento de Pedidos, Estoque e Funcionários

Este projeto é um sistema modular voltado para o gerenciamento de operações internas de um restaurante, contemplando controle de pedidos, atualização de estoque e gestão de funcionários. Os objetivos do projeto é criar um conjunto de serviços independentes que se comunicam entre si através de mensageria (RabbitMQ), garantindo:

1. Processamento assíncrono e confiável dos pedidos

2. Sincronização segura do estoque

3.  Manter o baixo acoplamento entre módulos

## Modelagem 

Interface Gráfica – envia requisições pro back-end (Serviço de Pedidos)
                |
        Serviço de Pedidos - recebe as requisições vindas da interface: Valida dados, reúne informações e publica eventos na fila
                |
        Fila / RabbitMQ - Correio 
           /           \
Serviço de Estoque      \ - Esse serviço “escuta” mensagens relevantes pra ele: Pedido criado, cancelado ou atualizado
                         \ 
                Serviço de Funcionários - Possível serviço, Pode cuidar de: autenticação de funcionários; permissões; cadastro; papéis (estoquista, gerente, vendedor)

## Planejamento

### Fase 1 — Fundamentos da Arquitetura

Definir o formato das mensagens usadas nos tópicos do RabbitMQ (JSON simples).

Criar o ambiente base: RabbitMQ; Banco para cada serviço; Redes internas

Criar repositório central com: padrão de logs; padrão de mensagens; padrões de comunicação entre serviços

### Fase 2 — Módulo de Pedidos 

Responsabilidades essenciais: criar pedido; validar itens enviados pela interface; registrar status inicial; publicar mensagens (pedido.criado e pedido.reserva.solicitada)

endpoints REST básicos: POST /pedidos; GET /pedidos/{id}

### Fase 3 — Módulo de Estoque

Responsabilidades essenciais: consumir mensagem pedido.reserva.solicitada; verificar quantidade disponível; reservar; emitir mensagens (estoque.reservado, estoque.insuficiente e estoque.baixo)

Estrutura mínima: tabela de produtos e tabela de movimentações

### Fase 4 — Módulo de Funcionários

Responsabilidades básicas: registrar funcionários; validar se o funcionário pode criar pedidos; consumir mensagens como: (pedido.criado e funcionario.acao.registrada)

Endpoints futuros: POST /funcionarios; GET /funcionarios/{id}; POST /funcionarios/login

### Fase 5 — Interface Gráfica (colega)

Versão mínima: criar pedido; listar pedidos; status do estoque; Usar chamadas REST pro Serviço de Pedidos.


### Fase 6 — Testes e Integração

Testes integrando todos os serviços e o fluxo 

### Fase 7 — Possíveis ajustes

novos serviços (relatório, recomendação, abastecimento automático);
novos eventos;
novas telas;
regras de permissão refinadas.