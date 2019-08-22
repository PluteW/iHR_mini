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
                      <el-date-picker v-model="form.scheduleTime"  type="date"  placeholder="请选择入职时间" clearable> </el-date-picker>
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
                    <el-date-picker v-model="form.timeBegin"  type="date"  placeholder="开始时间" clearable> </el-date-picker>
                  </el-form-item>
              </el-col>
              <el-col :span="6" label-width="80px">
                    <el-date-picker v-model="form.timeEnd"  type="date"  placeholder="结束时间" clearable> </el-date-picker>
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
const eduBackGround = ['专科及以上', '本科及以上', '研究生及以上', '博士', '留学生']
const jobCategray = ['开发岗', '管理岗', '技术岗', '人力岗', '财务岗']
const salaryOption = ['面议', '3000以上', '6000以上', '9000以上', '12000以上']
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
      region: ['山东', '北京', '上海', '广州'], // 应聘者来源
      salaryOption: salaryOption, // 薪资范围
      jobCategray: jobCategray, // 岗位范围
      eduBackGround: eduBackGround // 学历选项
    }
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
          url: 'ccs/', // 请求数据地址
          data: {
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
        function (res) { // 如果返回数据，则放到表格中
          if (res.data) {
            Message({
              type: 'success',
              message: '发布成功！'
            })
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
      loadingInstance.close()
    },
    onSubmit () { // 发布按钮，非提交
      console.log(this.form)
      this.handleSubmit()
      this.reset()
    },
    reset () { // 重置按钮
      let loadingInstance = Loading.service({
        text: '请稍等...',
        target: document.querySelector('.loadingtext')
      })
      this.name = '' // 职位
      this.region = '' // 岗位工作地
      this.scheduleTime = '' // 到岗时间
      this.jobCategray = '' // 职务类别
      this.eduBackGround = '' // 岗位学历要求
      this.account = '' // 岗位人数上限
      this.timeBegin = '' // 招聘开始时间
      this.timeEnd = '' // 招聘结束时间
      this.salary = '' // 薪资待遇
      loadingInstance.close()
    }
  }
}
</script>
