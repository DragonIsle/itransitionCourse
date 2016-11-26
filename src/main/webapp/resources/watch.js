/**
 * Created by saul on 11/26/16.
 */
'use strict';

var watch = angular.module('watch', []);

watch.controller("WatchController", function ($scope, $http) {
    function getId() {
        return window.location.search.substring(1).split("=")[1];
    }
    $http.get("creative/tag", {params: {creativeId: getId()}}).success(function (data) {
        $scope.tags=data;
    });
    $http.get("creative/chapter", {params: {creativeId: getId()}}).success(function (data) {
        $scope.chapters=data;
    });
    $http.get("creative/single",{params: {creativeId: getId()}}).success(function (data) {
        $scope.cr=data;
    });
    $scope.rate=function () {
        var numb=$scope.rating.split("/10");
        $http.get("creative/rate", {params: {creativeId: getId(), rating: numb[0]}})
    }
});