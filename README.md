# warth
模块可插拔的快速开发平台

搭建适合各种规模业务系统的基础开发平台，包含core-framework（具体业务）、core-authentication（权限认证）、core-domain（领域模型）、core-base（基础组件）、core-gen（代码生成），其中core-framework提供spring框架搭建，用户只需要专注具体业务即可，core-domain用来放置接口和领域模型，core-base主要是一些共用的方法组件、core-authentication是基于shiro进行用户权限认证，core-gen基于freemarker来生成业务代码；其中，权限认证模块和代码生成模块可以通过添加或者删除依赖来决定是否使用，生成代码的逻辑是总结了大多前后端分离系统里面常用的数据库增删改查代码编写的模板，做到最大限度的适用。
