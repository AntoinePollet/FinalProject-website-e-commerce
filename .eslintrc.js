module.exports = {
  parserOptions: {
    ecmaVersion: 2017
  },
  env: {
    es6: true
  },
  extends: ['standard', 'plugin:vue/recommended'],
  plugins: ['babel', 'eslint-plugin-jsdoc'],
  rules: {
    'babel/no-unused-expressions': 0,
    'vue/html-closing-bracket-newline': 0,
    'vue/max-attributes-per-line': 4,
    'vue/script-indent': ['error', 2, { baseIndent: 1, switchCase: 1 }],
    'vue/html-self-closing': [
      'error',
      {
        html: {
          void: 'always',
          normal: 'always',
          component: 'always'
        },
        svg: 'always',
        math: 'always'
      }
    ]
  },
  overrides: [
    {
      files: ['*.vue'],
      rules: {
        indent: 'off'
      }
    }
  ]
}
