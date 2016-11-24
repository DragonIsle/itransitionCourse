/**
 * Created by saul on 11/22/16.
 */
'use strict';

var personal = angular.module('personal', []);

personal.controller('PageController', function ($scope, $http) {
    $http.post('session').success(function (data) {
        $scope.user = data;
        $scope.isAdmin=($scope.user.role==="ADMIN");
    });
    $http({
            url: 'creative',
            method: "GET"
        }
    ).success(function (data) {
        $scope.creatives=data;
    });
    $scope.addCreative=function () {
        $http.put("user", $scope.user).success(function (data) {
            $scope.creatives=data;
        })
    };
    $scope.getCreator=function (creative) {
        $http.post('creative/creator', {params:{creativeId: creative.id}}).success(function (data) {
            creative.creator=data;
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