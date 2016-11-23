/**
 * Created by saul on 11/22/16.
 */
'use strict';

var personal = angular.module('personal', []);

personal.controller('PageController', function ($scope, $http) {
    $http({
            url: 'user',
            method: "GET",
            params: {
                login: findGetParameter("login")
            }
        }
    ).success(function (data) {
        $scope.user=data;
    });
    $http({
            url: 'creative',
            method: "GET",
            params: {
                login: findGetParameter("login")
            }
        }
    ).success(function (data) {
        $scope.creatives=data;
    });
    function findGetParameter(parameterName) {
        var result = null,
            tmp = [];
        location.search
            .substr(1)
            .split("&")
            .forEach(function (item) {
                tmp = item.split("=");
                if (tmp[0] === parameterName) result = decodeURIComponent(tmp[1]);
            });
        return result;
    }
    $scope.addCreative=function () {
        $http.put("user", $scope.user).success(function (data) {
            $scope.creatives=data;
        })
    };
    $scope.remove=function (creative) {
        $http.delete('creative', {params: {creativeId: creative.id}}).success(function (data) {
            $scope.creatives=data;
            $http({
                    url: 'creative',
                    method: "GET",
                    params: {
                        login: $scope.user.login
                    }
                }
            ).success(function (data) {
                $scope.creatives=data;
            });
        })
    };
    $scope.redact=function (creative) {
        window.location="creativePage.html?id="+creative.id;
    }
});

personal.controller('CreativeController', function ($scope, $http) {
    $http.get("creative/tag", {params: {creativeId: 3}}).success(function (data) {
        $scope.tags=data;
    });
    $http.get("creative/chapter").success(function (data) {
        $scope.chapters=data;
    })
});