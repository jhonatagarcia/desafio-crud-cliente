var crud = angular.module("crud", []);

crud.service(function ($httpProvider) {
  // Adiciona um interceptor para adicionar os cabeçalhos CORS em todas as solicitações
  $httpProvider.interceptors.push(function () {
    return {
      headers: function (config) {
        // Adiciona os cabeçalhos CORS necessários
        config.headers["Access-Control-Allow-Origin"] = "*";
        config.headers["Access-Control-Allow-Methods"] =
          "GET, POST, OPTIONS, PUT, PATCH, DELETE";
        config.headers["Access-Control-Allow-Headers"] =
          "Origin, X-Requested-With, Content-Type, Accept, Authorization";
        return config;
      },
    };
  });
});

crud.controller("controller", function ($scope, $http) {
  $scope.novoCliente = {};
  $scope.clienteSelecionado = {};

  $scope.password = "";
  $scope.passwordStrength = "";
  $scope.passwordQualidade = 0;

  $scope.clientes = [];

  $scope.salvar = function () {
    var dados = {
      userClient: "",
      passwordClient: "",
    };

    $scope.clientes.push($scope.novoCliente);
    $scope.novoCliente = {};

    $http
      .post("http://localhost:8080/Client", dados)
      .then(function (response) {
        // Sucesso na requisição
        console.log("Resposta do servidor:", response.data);
      })
      .catch(function (error) {
        // Erro na requisição
        console.error("Erro na requisição:", error);
      });
  };

  $scope.getCliente = function () {
    $http
      .get("http://localhost:8090/Client")
      .then(function (response) {
        $scope.clienteTeste = response.data;
        console.log(response.data);
      })
      .catch(function (error) {
        console.error("Erro ao obter dados do serviço Java:", error);
      });
  };

  $scope.selecionaCliente = function (cliente) {
    $scope.clienteSelecionado = cliente;
  };

  $scope.alterarCliente = function () {};

  $scope.excluirCliente = function () {
    $scope.clientes.splice(
      $scope.clientes.indexOf($scope.clienteSelecionado),
      1
    );
  };

  // Função para carregar dados do backend
  $scope.carregarDadosDoBackend = function () {
    $http
      .get("http://localhost:8090/Client")
      .then(function (response) {
        // Lidar com a resposta bem-sucedida aqui
        console.log(response.data);
      })
      .catch(function (error) {
        // Lidar com erros aqui
        console.error("Erro na requisição:", error);
      });
  };

  // Chame a função para carregar dados ao inicializar o controlador, se necessário
  $scope.carregarDadosDoBackend();

  return $http.get("Client");
});
