/**
 * Created by saul on 11/22/16.
 */
'use strict';

var deleter = angular.module('deleter', []);

deleter.controller('DeleteController', function ($http, $scope) {
    $http.get("user/all").success(function (data) {
        $scope.users=data;
    });
    $scope.remove=function (user){
       $http.delete('user', {params:{login :user.login}}).success(function (data) {
          $scope.users=data;
       })
    }
});