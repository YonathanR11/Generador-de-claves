app.service('cuentaService', function ($q, factory) {
    const SELF = this;
    const PATH = "cuentas";

    SELF.post = (cadena) => {
        return $q((success, error) => {
            factory.post(PATH, cadena).then(
                (resolve) => {
                    success(resolve) 
                },
                (reject) => {
                    error(reject)
                })
        })
    }
})