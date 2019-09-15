<template>
    <div class="allBack">
      <div class="left">
        <el-menu default-active='2' class="list" @select="handleSelect">
          <el-menu-item index="1" name="home">
            <template slot="title">
              <i class="el-icon-s-home"></i>
              <span slot="title">首页</span>
            </template>
          </el-menu-item>
          <el-menu-item index="2" name="mypage">
            <i class="el-icon-user"></i>
            <span slot="title">我的</span>
          </el-menu-item>
          <el-menu-item index="3" name="infor">
            <i class="el-icon-bell"></i>
            <span slot="title">消息</span>
          </el-menu-item>
          <el-menu-item index="4" name="errorReport">
            <i class="el-icon-phone-outline"></i>
            <span slot="title">报障</span>
          </el-menu-item>
          <el-menu-item index="5" name="setting">
            <i class="el-icon-setting"></i>
            <span slot="title">设置</span>
          </el-menu-item>
        </el-menu>
      </div>
      <div>
        <div v-if="activeName === 'myInfor'">
          <myInfor></myInfor>
        </div>
        <div v-if="activeName === 'newInfor'">
          <newInfor></newInfor>
        </div>
        <div v-if="activeName === 'errorReport'">
          <errorReport></errorReport>
        </div>
        <div v-if="activeName === 'settings'">
          <settings></settings>
        </div>
      </div>
    </div>
</template>

<script>
import myInfor from './myInfor.vue'
import errorReport from './errorReport.vue'
import newInfor from './newInfor.vue'
import settings from './settings'
export default {
  components: {
    myInfor,
    errorReport,
    newInfor,
    settings
  },
  data () {
    return {
      activeName: 'myInfor' // 默认为选定“我的”页面
    }
  },
  methods: {
    handleSelect (key, keyPath) {
      let that = this
      if (key === '1') {
        window.location.href = '/hmain'
      } else if (key === '2') {
        that.activeName = 'myInfor'
      } else if (key === '3') {
        that.activeName = 'newInfor'
      } else if (key === '4') {
        that.activeName = 'errorReport'
      } else if (key === '5') {
        that.activeName = 'settings'
      }
    }
  }
}
</script>

<style>
.allBack{
  width: 100%;
  height: 100%;
}
.left{
  padding-top: 7%;
  float: left;
  width: 20%;
  height: 100%;
}
.right{
  float: right;
  width: 18%;
}
.list{
  height: 100%;
  width: 100%;
  text-align: center
}
</style>
