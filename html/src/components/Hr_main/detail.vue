<template>
    <div>{{message}}</div>
</template>

<script>
export default {
  data () {
    return {
      message: localStorage.getItem('data')
    }
  },
  mounted: function () {
    // 用$on事件来接收参数
    this.$bus.on('val', (data) => {
      console.log(data)
      localStorage.setItem('data', data)
    })
  }
}
</script>
