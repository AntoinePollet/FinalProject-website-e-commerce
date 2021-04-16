const HtmlWebpackPlugin = require('html-webpack-plugin')
const VueLoaderPlugin = require('vue-loader/lib/plugin')
const MiniCssExtractPlugin = require('mini-css-extract-plugin')
const path = require('path')
const webpack = require('webpack')

module.exports = (env, argv) => {
  console.log(env, argv)
  return {
    entry: './src/main.js',
    output: {
      path: path.resolve(__dirname, './dist'),
      filename: '[name].bundle.js'
    },
    module: {
      rules: [
        { test: /\.vue$/, use: 'vue-loader' },
        { test: /\.js$/, use: 'babel-loader' },
        {
          test: /\.s(c|a)ss$/,
          use: [
            MiniCssExtractPlugin.loader,
            'css-loader',
            {
              loader: 'sass-loader',
              options: {
                sourceMap: true,
                additionalData: '@import "./src/style/variables";'
              }
            }
          ]
        },
        { test: /\.css$/, use: ['vue-style-loader', 'css-loader'] },

        {
          test: /\.(gif|png|jpe?g|svg)$/i,
          loader: 'file-loader',
          options: {
            name: 'assets/imgs/[name].[ext]',
            publicPath: argv.mode === 'production' ? '/' : '/dist/'
          }
        },
        {
          test: /\.svg$/,
          use: ['babel-loader', 'vue-svg-loader']
        }
      ]
    },
    devServer: {
      open: true,
      hot: true,
      historyApiFallback: true
      //host: '192.168.0.102'
    },
    plugins: [
      new MiniCssExtractPlugin({
        filename: '[name].css',
        chunkFilename: '[id].css'
      }),

      new HtmlWebpackPlugin({
        template: './src/index.html',
        filename: 'index.html'
      }),
      new VueLoaderPlugin(),

      new webpack.HotModuleReplacementPlugin()
    ]
  }
}
