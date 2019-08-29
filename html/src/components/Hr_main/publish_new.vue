<template>
    <div>
      <div style="margin: 80px auto auto 10%">
        <el-form :model="form" label-width="120px">
          <el-row>
              <el-col :span="6">
                  <el-form-item label="岗位名称：" label-width="100px" label-position = 'right'>
                      <el-input v-model="form.name" placeholder="请输入岗位名"></el-input>
                  </el-form-item>
              </el-col>
              <el-col :span="7">
                  <el-form-item label="工作地点：" label-width="100px" label-position = 'right'>
                      <el-select v-model="form.region" placeholder="请选择工作地点" clearable>
                          <el-option v-for="(item,index) in region" :key="index"  :value="index" :label="item" ></el-option>
                      </el-select>
                  </el-form-item>
              </el-col>
              <el-col :span="5">
                  <el-form-item label="入职时间：" label-width="100px" label-position = 'right'>
                      <el-date-picker v-model="form.scheduleTime" value-format="yyyy-MM-dd" type="date"  placeholder="请选择入职时间" clearable> </el-date-picker>
                  </el-form-item>
              </el-col>
          </el-row>
          <el-row style="margin-top: 20px">
              <el-col :span="6" label-width="100px">
                  <el-form-item label="类别：" label-position = 'right'>
                        <el-select v-model="form.jobCategray" placeholder="请选择类别" clearable>
                          <el-option v-for="(item,index) in jobCategray" :key="index" :value="index" :label="item" ></el-option>
                      </el-select>
                  </el-form-item>
              </el-col>
              <el-col :span="6" label-width="100px">
                  <el-form-item label="学历要求：" label-position = 'right'>
                      <el-select v-model="form.eduBackGround" placeholder="学历要求" clearable>
                          <el-option v-for="(item,index) in eduBackGround" :key="index" :value="index" :label="item" ></el-option>
                      </el-select>
                  </el-form-item>
              </el-col>
              <el-col :span="6"  label-width="100px" >
                  <el-form-item label="招聘人数：" label-position = 'right'>
                      <el-input v-model="form.account" placeholder="请输入人数"></el-input>
                  </el-form-item>
              </el-col>
          </el-row>
          <el-row style="margin-top: 20px">
              <el-col :span="6">
                  <el-form-item label-width="100px" label="有效时间：" label-position = 'right'>
                    <el-date-picker v-model="form.timeBegin" value-format="yyyy-MM-dd" type="date"  placeholder="开始时间" clearable> </el-date-picker>
                  </el-form-item>
              </el-col>
              <el-col :span="6">
                    <el-date-picker v-model="form.timeEnd" value-format="yyyy-MM-dd" type="date"  placeholder="结束时间" clearable> </el-date-picker>
              </el-col>
              <el-col :span="6" label-width="150px">
                  <el-form-item label="薪资待遇(月)：" label-position = 'right'>
                      <el-select v-model="form.salary" placeholder="请选择月薪" clearable>
                          <el-option v-for="(item,index) in salaryOption" :key="index" :value="index" :label="item" ></el-option>
                      </el-select>
                  </el-form-item>
              </el-col>
          </el-row>
          <el-form-item style="margin-left:60%">
              <el-button type="primary" @click="onSubmit" style="margin-right: 7%">发布</el-button>
              <el-button @click="reset">重置</el-button>
          </el-form-item>
        </el-form>
      </div>
  </div>
</template>

<script>
import { Loading, Message } from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axios from 'axios'
import Qs from 'qs'
// let loadingInstance = Loading.service({
//   text: '请稍等...',
//   target: document.querySelector('.loadingtext')
// })
export default {
  name: 'polling',
  props: ['cotent'],
  data () {
    return {
      message: this.cotent,
      form: {
        name: '', // 职位
        region: '', // 岗位工作地
        scheduleTime: '', // 到岗时间
        jobCategray: '', // 职务类别
        eduBackGround: '', // 岗位学历要求
        account: '', // 岗位人数上限
        timeBegin: '', // 招聘开始时间
        timeEnd: '', // 招聘结束时间
        salary: '' // 薪资待遇
      },
      region: [], // 应聘者来源
      salaryOption: [], // 薪资范围
      jobCategray: [], // 岗位范围
      eduBackGround: [] // 学历选项
    }
  },
  mounted () { // 表格选择项初始化
    let _self = this
    let loadingInstance = Loading.service({
      text: '请稍等...',
      target: document.querySelector('.loadingtext')
    })
    axios(
      {
        method: 'post',
        url: '/hr/addNew/init', // 请求数据地址
        data: {
          form: '' // 申请标志符
        },
        transformRequest: [function (data) {
          var params = Qs.stringify(data, { arrayFormat: 'brackets' })
          return params
        }],
        headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
        timeout: 15000
      }
    ).then(
      function (res) { // 如果返回数据，则放到表格中
        if (res.data) {
          _self.region = res.data.region // 可选择的工作地区
          _self.salaryOption = res.data.salaryOption // 可选择的薪资水平
          _self.jobCategray = res.data.jobCategray // 可选择的工作分类
          _self.eduBackGround = res.data.eduBackGround // 可选则的教育背景
          loadingInstance.close()
        }
      }, function (response) { // 如果返回错误，则提错
        if (response) {
          loadingInstance.close()
          Message({
            type: 'error',
            message: response
          })
        }
      }
    )
  },
  methods: {
    handleSubmit () { // 处理发布动作
      let _self = this
      let loadingInstance = Loading.service({
        text: '请稍等...',
        target: document.querySelector('.loadingtext')
      })
      axios(
        {
          method: 'post',
          url: '/hr/addNew/add', // 请求数据地址
          data: {
            region: JSON.stringify(_self.region), // 可选择的工作地区
            salaryOption: JSON.stringify(_self.salaryOption), // 可选择的薪资水平
            jobCategray: JSON.stringify(_self.jobCategray), // 可选择的工作分类
            eduBackGround: JSON.stringify(_self.eduBackGround), // 可选则的教育背景
            form: _self.form // 保存了数据的对象
          },
          transformRequest: [function (data) {
            var params = Qs.stringify(data, { arrayFormat: 'brackets' })
            return params
          }],
          headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
          timeout: 15000
        }
      ).then(
        function (res) { // 判断是否成功发布
          if (res) {
            loadingInstance.close()
            Message({
              type: 'success',
              message: '发布成功！'
            })
            this.reset()
          }
        }, function (response) { // 如果返回错误，则提错
          if (response) {
            loadingInstance.close()
            Message({
              type: 'error',
              message: response
            })
          }
        }
      )
      loadingInstance.close()
    },
    onSubmit () { // 发布按钮，非提交
      console.log(this.form)
      this.handleSubmit()
    },
    reset () { // 重置按钮
      let loadingInstance = Loading.service({
        text: '请稍等...',
        target: document.querySelector('.loadingtext')
      })
      this.form.name = '' // 职位
      this.form.region = '' // 岗位工作地
      this.form.scheduleTime = '' // 到岗时间
      this.form.jobCategray = '' // 职务类别
      this.form.eduBackGround = '' // 岗位学历要求
      this.form.account = '' // 岗位人数上限
      this.form.timeBegin = '' // 招聘开始时间
      this.form.timeEnd = '' // 招聘结束时间
      this.form.salary = '' // 薪资待遇
      loadingInstance.close()
    }
  }
}
</script>
