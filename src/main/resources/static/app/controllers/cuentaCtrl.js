app.controller("cuentaControlador", function ($scope, cuentaService) {

    $scope.cargando = true;
    $scope.expresion = null;
    let campo = document.getElementById('inputExpresion');
    let btn = document.getElementById('btnExpre');

    $scope.submitExpresion = function (valid, expresion) {
        if (valid) {
            $scope.insertarCuentas(expresion);
        } else {
            alert("No valido");
        }
    }

    $scope.insertarCuentas = function (expresion) {
        console.log("ok: ", expresion);
        $scope.cargando = false;
        campo.readOnly = true;
        btn.disabled = true;

        if (confirm("¿Desea iniciar el proceso?")) {
            cuentaService.post($scope.expresion).then((data) => {
                if (data === true) {
                    // Listo
                    $scope.cargando = true;
                    campo.readOnly = false;
                    btn.disabled = false;
                    $scope.cargando = true;
                    alert("¡Listo!");
                } else {
                    // Algo salio mal
                    $scope.cargando = true;
                    campo.readOnly = false;
                    btn.disabled = false;
                    alert("¡Algo salio mal!");
                }
            }, (reject) => {
                // Error
                $scope.cargando = true;
                campo.readOnly = false;
                btn.disabled = false;
                alert("Error: " + reject);
            });
        } else {
            $scope.cargando = true;
            alert("¡Cancelado!");
        }
    }

    $scope.activarDesactivar = function (valor) {
        if (valor = 1) {
            campo.readOnly = false;
            btn.disabled = false;
        } else {
            campo.readOnly = true;
            btn.disabled = true;
        }
    }

});