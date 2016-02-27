<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Daniil
  Date: 2/26/2016
  Time: 7:29 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- images -->
<spring:url value="/resources/images/genesys-logo.png" var="logo" />

<!-- Css paths -->
<spring:url value="/resources/css/node_modules/todomvc-app-css/index.css" var="indexCss" />
<spring:url value="/resources/css/node_modules/todomvc-common/base.css" var="baseCss" />

<!-- Js paths -->
<spring:url value="/resources/js/node_modules/angular/angular.js" var="angularJs" />
<spring:url value="/resources/js/node_modules/angular-route/angular-route.js" var="angularRouteJs" />
<spring:url value="/resources/js/node_modules/todomvc-common/base.js" var="baseJs" />
<spring:url value="/resources/js/node_modules/angular-resource/angular-resource.js" var="angularResourceJs" />

<spring:url value="/resources/js/app.js" var="appJs" />

<spring:url value="/resources/js/controllers/todoCtrl.js" var="ctrlJs" />

<spring:url value="/resources/js/directives/todoEscape.js" var="escapeJs" />
<spring:url value="/resources/js/directives/todoFocus.js" var="focusJs" />

<spring:url value="/resources/js/services/todoStorage.js" var="serviceJs" />


<html>
<head>
    <meta charset="utf-8">
    <title>AngularJS • TodoMVC</title>
    <link rel="stylesheet" href="${baseCss}">
    <link rel="stylesheet" href="${indexCss}">
    <style>[ng-cloak] { display: none; }</style>
</head>
<body ng-app="todomvc">
    <ng-view />

    <script type="text/ng-template" id="todomvc-index.html">
        <section id="todoapp">
            <header id="header">
                <h1>Todos for <img src="${logo}" /></h1>
                <form id="todo-form" ng-submit="addTodo()">
                    <input id="new-todo" placeholder="What needs to be done?" ng-model="newTodo" ng-disabled="saving" autofocus>
                </form>
            </header>
            <section id="main" ng-show="todos.length" ng-cloak>
                <input id="toggle-all" type="checkbox" ng-model="allChecked" ng-click="markAll(allChecked)">
                <label for="toggle-all">Mark all as complete</label>
                <ul id="todo-list">
                    <li ng-repeat="todo in todos | filter:statusFilter track by $index" ng-class="{completed: todo.completed, editing: todo == editedTodo}">
                        <div class="view">
                            <input class="toggle" type="checkbox" ng-model="todo.completed" ng-change="toggleCompleted(todo)">
                            <label ng-dblclick="editTodo(todo)">{{todo.title}}</label>
                            <button class="destroy" ng-click="removeTodo(todo)"></button>
                        </div>
                        <form ng-submit="saveEdits(todo, 'submit')">
                            <input class="edit" ng-trim="false" ng-model="todo.title" todo-escape="revertEdits(todo)" ng-blur="saveEdits(todo, 'blur')" todo-focus="todo == editedTodo">
                        </form>
                    </li>
                </ul>
            </section>
            <footer id="footer" ng-show="todos.length" ng-cloak>
                        <span id="todo-count"><strong>{{remainingCount}}</strong>
                            <ng-pluralize count="remainingCount" when="{ one: 'item left', other: 'items left' }"></ng-pluralize>
                        </span>
                <ul id="filters">
                    <li>
                        <a ng-class="{selected: status == ''} " href="#/">All</a>
                    </li>
                    <li>
                        <a ng-class="{selected: status == 'active'}" href="#/active">Active</a>
                    </li>
                    <li>
                        <a ng-class="{selected: status == 'completed'}" href="#/completed">Completed</a>
                    </li>
                </ul>
                <button id="clear-completed" ng-click="clearCompletedTodos()" ng-show="completedCount">Clear completed</button>
            </footer>
        </section>
        <footer id="info">
            <p>Double-click to edit a todo</p>
            <p>Credits:
                <a href="http://twitter.com/cburgdorf">Christoph Burgdorf</a>,
                <a href="http://ericbidelman.com">Eric Bidelman</a>,
                <a href="http://jacobmumm.com">Jacob Mumm</a> and
                <a href="http://blog.igorminar.com">Igor Minar</a>
            </p>
            <p>Part of <a href="http://todomvc.com">TodoMVC</a></p>
        </footer>
    </script>
    <script src="${baseJs}"></script>
    <script src="${angularJs}"></script>
    <script src="${angularRouteJs}"></script>
    <script src="${angularResourceJs}"></script>
    <script src="${appJs}"></script>
    <script src="${ctrlJs}"></script>
    <script src="${serviceJs}"></script>
    <script src="${focusJs}"></script>
    <script src="${escapeJs}"></script>
</body>
</html>
