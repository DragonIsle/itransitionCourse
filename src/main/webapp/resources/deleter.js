/**
 * Created by saul on 11/22/16.
 */
'use strict';

var deleter = angular.module('deleter', []);

deleter.controller('DeleteController', function ($http, $scope) {
    $http.get("session/style").success(function (data) {
        $scope.style=data;
    });
    $http.post('session').success(function (data) {
        $scope.us=data;
    });
    $http.get("user/all").success(function (data) {
        $scope.users=data;
    });
    $scope.remove=function (user){
       $http.delete('user', {params:{login :user.login}}).success(function (data) {
          $scope.users=data;
       })
    }
});