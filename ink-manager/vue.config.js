

module.exports = {
  devServer: {
    port: process.env.NODE_ENV == "production" ? 9090 : 9090,
    proxy: {
        '/api': {
            target: process.env.VUE_APP_SERVER,
            // 允许跨域
            changeOrigin: true,
            // ws: true,
            // 日志级别
            // logLevel: "debug",
            pathRewrite: {
                '^/api': process.env.VUE_APP_SERVER_CONTEXT_PATH
            }
        }
    }
  }
}