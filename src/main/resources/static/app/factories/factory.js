app.factory("factory", function ($http, $q) {
    return {
        post: function ($url, data) {
            return $http({
                url: '/' + $url,
                method: 'POST',
                headers: {
                    'Content-Type': 'aplication/json'
                },
                data : data
            }).then((succes) => {
                return succes.data
            }, (error) => {
                return $q.reject(error);
            })
        }
    }
})