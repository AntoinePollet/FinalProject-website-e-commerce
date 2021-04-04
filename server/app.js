const stripe = require('stripe')(
  'sk_test_51I93oFEAsAvLZg5lsmX0nQlGO1c4DBA4kiSDNJYVVjvvc4bEaEoV5l6M7tNUJ8H7DFmz49IcuRfO9eYQqFcU76ex00a67qWPle'
)
const express = require('express')
const bodyParser = require('body-parser')
const mongoose = require('mongoose')
const cors = require('cors')
const app = express()
app.use(express.static('.'))

mongoose
  .connect(
    'mongodb+srv://admin:C8TsBG76rhM6CIQ4@kyokyu.vyflg.mongodb.net/KyoKyu?retryWrites=true&w=majority',
    { useNewUrlParser: true, useUnifiedTopology: true, useFindAndModify: false }
  )
  .then(() => console.log('Connexion à MongoDB réussie !'))
  .catch(() => console.log('Connexion à MongoDB échouée !'))

app.use(bodyParser.json())
app.use(cors())

app.use((req, res, next) => {
  res.setHeader('Access-Control-Allow-Origin', '*')
  res.setHeader(
    'Access-Control-Allow-Headers',
    'Origin, X-Requested-With, Content, Accept, Content-Type, Authorization'
  )
  res.setHeader(
    'Access-Control-Allow-Methods',
    'GET, POST, PUT, DELETE, PATCH, OPTIONS'
  )
  next()
})

const YOUR_DOMAIN = 'http://localhost:8080'

app.post('/create-checkout-session', async (req, res) => {
  const session = await stripe.checkout.sessions.create({
    payment_method_types: ['card'],
    line_items: [
      {
        price_data: {
          currency: 'usd',
          product_data: {
            name: 'Stubborn Attachments',
            images: ['https://i.imgur.com/EHyR2nP.png']
          },
          unit_amount: 2000
        },
        quantity: 1
      }
    ],
    mode: 'payment',
    success_url: `${YOUR_DOMAIN}/success`,
    cancel_url: `${YOUR_DOMAIN}/cancel`
  })
  res.json({ id: session.id })
})

// app.use('/api/songs', songs)
module.exports = app
