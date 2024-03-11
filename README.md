# Documentação do app de consulta de filmes

## Introdução

Este documento descreve a arquitetura, o design, as funcionalidades e os testes do app de consulta de filmes, desenvolvido para a plataforma Android, utilizando a linguagem kotlin e as bibliotecas mvvm, navigation, koin, retrofit e mockk. O objetivo do app é permitir que os usuários pesquisem, visualizem e avaliem filmes de diferentes gêneros e categorias, utilizando a API do The Movie Database (TMDB).

## Arquitetura

O app segue os princípios de clean architecture, que consiste em separar as camadas de responsabilidade do software, de forma a torná-lo mais modular, testável e independente de frameworks e detalhes de implementação. As camadas utilizadas no app são:

- Presentation: responsável pela interface gráfica do app, que exibe os dados e interage com o usuário. Utiliza o padrão MVVM (Model-View-ViewModel) para separar a lógica de apresentação da lógica de negócio. Os componentes principais dessa camada são as activities, os fragments, os view models e os adapters.
- Domain: responsável pela lógica de negócio do app, que define as regras e os casos de uso do domínio do problema. Utiliza o padrão de design de interatores, que são classes que encapsulam a execução de cada caso de uso. Os componentes principais dessa camada são os interatores, as entidades e os repositórios.
- Data: responsável pela persistência e acesso aos dados do app, que podem vir de fontes locais ou remotas. Utiliza o padrão de design de repositórios, que são interfaces que abstraem a origem e o tipo dos dados. Os componentes principais dessa camada são as implementações dos repositórios, as fontes de dados e os mapeadores.

As camadas se comunicam por meio de interfaces e modelos de dados, que são definidos na camada de domínio e implementados ou utilizados nas demais camadas. A injeção de dependências é feita por meio da biblioteca koin, que permite a criação e o gerenciamento dos objetos das camadas de forma desacoplada e flexível.

## Design

O app utiliza o Material Design como guia de estilo para a interface gráfica, seguindo as recomendações de cores, ícones, tipografia, animações e componentes. O app também utiliza a biblioteca navigation para gerenciar a navegação entre as telas, que são organizadas em um grafo de navegação. O app possui as seguintes telas principais:

- Home: tela inicial do app, que exibe uma lista de filmes populares, em cartaz, ou recomendados para o usuário. O usuário pode filtrar os filmes por gênero ou categoria, ou pesquisar por um filme específico. O usuário também pode acessar o menu lateral, que permite navegar para outras telas do app, como a de favoritos, a de configurações ou a de sobre.
- Detalhe: tela que exibe os detalhes de um filme selecionado, como o título, a sinopse, o elenco, a direção, o gênero, a classificação, o trailer e as avaliações. O usuário pode marcar o filme como favorito, compartilhar o filme com outros apps, ou avaliar o filme com uma nota e um comentário.

## Funcionalidades

O app utiliza a API do TMDB para obter os dados dos filmes, como o título, a sinopse, o elenco, a direção, o gênero, a classificação, o trailer e as avaliações. O app utiliza o retrofit para fazer as requisições HTTP para a API do TMDB, e o gson para converter os dados JSON em objetos kotlin. O app utiliza o glide para carregar e exibir as imagens dos filmes, e o youtube player para reproduzir os trailers dos filmes.

## Testes

O app possui testes unitários para as camadas de domínio e de dados, utilizando a biblioteca mockk para criar mocks das interfaces e dos objetos das camadas. O app também possui testes de interface para as telas principais, utilizando a biblioteca espresso para simular as interações do usuário com os elementos da interface. Os testes de interface verificam se as telas estão exibindo os dados corretamente, e se a navegação entre as telas está funcionando conforme o esperado.
