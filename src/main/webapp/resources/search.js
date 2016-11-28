/**
 * Created by saul on 11/27/16.
 */
'use strict';

var search = angular.module('search', []);

search.controller('SearchController', function ($scope, $http) {
    function getName() {
        return window.location.search.substring(1).split("=")[1];
    }
    $scope.style="css/cyborg.css";
    $http.get("session/style").success(function (data) {
        $scope.style=data;
    });
    $http.get("creative/get/bytag", {params:{name: getName()}}).success(function (data) {
        $scope.creatives=data;
        $scope.tag=getName();
    });
    $scope.redirect=function (creative) {
        window.location="creativeWatch.html?id="+creative.id;
    };
});