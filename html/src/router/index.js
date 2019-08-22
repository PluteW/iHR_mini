import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import login from '@/components/login'
import Elogin from '@/components/Elogin'
import Emain from '@/components/Employee_main/Emain'
import Hmain from '@/components/Hr_main/Hmain'

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
      path: '/Hmain',
      name: 'Hmain',
      component: Hmain
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
