import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import login from '@/components/login'
import Elogin from '@/components/Elogin'
import Emain from '@/components/Emain'
import main from '@/components/main'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/hello',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/Emain',
      name: 'Elogin',
      component: Emain
    },
    {
      path: '/main',
      name: 'main',
      component: main
    },
    {
      path: '/e',
      name: 'Elogin',
      component: Elogin
    },
    {
      path: '*',
      name: 'login',
      component: login
    }
  ],
  mode: 'history'
})
