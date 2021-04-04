<template>
	<v-main>
		<div v-if="cart.length != 0" id="element-to-print">
			<v-container class="col-8">
				<div class="title-product d-flex">
					<h4 class="col-6 text-left pl-0">Produit</h4>
					<h4 class="col-2 text-right">Price</h4>
					<h4 class="col-2 text-right">Quantity</h4>
					<h4 class="col-2 text-right pr-0">Total</h4>
				</div>
				<div v-for="item in cart" :key="item.id" class="item-infos d-flex">
					<div class="col-6 text-left pl-0 pb-0">
						<v-img
							contain
							:src="item.pictures[0]"
							:style="{ height: '100px', width: '100px' }"
						/>
						<p class="mb-0">{{ item.name }}</p>
					</div>
					<div class="col-2 text-right">{{ item.price }} €</div>
					<div class="col-2 text-right">{{ item.quantity }}</div>
					<div class="col-2 text-right pr-0">
						{{ item.price * item.quantity }} €
					</div>
				</div>
				<div class="d-flex justify-end">
					<div>
						<h2>
							Subtotal : <strong>{{ totalPrice }}</strong> €
						</h2>
						<v-card-actions class="justify-center"
							><v-btn
								class="pink light-gray px-3 py-3"
								@click="$router.push({ name: 'livraison' })"
								text
								color="white"
								>Checkout</v-btn
							></v-card-actions
						>
						<v-card-actions class="justify-center">
							<v-btn class="teal" @click="pdf()" text color="white"
								>download</v-btn
							></v-card-actions
						>
					</div>
				</div>
			</v-container>
		</div>

		<div v-else>Pas d'item dans le Panier</div>
	</v-main>
</template>

<script>
import { mapGetters } from 'vuex';
import colors from '../data/colors.json';
import { jsPDF } from 'jspdf';
import 'jspdf-autotable';

export default {
	name: 'Cart',
	data() {
		return {
			colors: colors
		};
	},
	computed: {
		...mapGetters({
			cart: 'cart/getCart'
		}),
		totalPrice() {
			return this.cart.reduce((acc, item) => {
				acc = acc + item.price * item.quantity;
				return acc;
			}, 0);
		},
		getCartItem() {
			return this.cart.map(item => {
				let arr = new Array();
				arr.push(
					item.name,
					item.category,
					item.quantity,
					item.color,
					item.price + ' €'
				);
				return arr;
			});
		}
	},
	methods: {
		removeItem(item, index) {
			this.$store.dispatch('cart/removeCartItem', index);
		},
		priceItem(item) {
			return item.price * item.quantity;
		},
		colorItem(nameColor) {
			for (let color in this.colors) {
				if (this.colors[color].name === nameColor) {
					const theChosenColor = this.colors[color].color;
					return theChosenColor;
				}
			}
		},
		pdf() {
			const doc = new jsPDF();

			doc.text('KyoKyu', 100, 10);
			doc.setFontSize(12);
			doc.text('Name', 40, 20);
			doc.text('Adresse', 40, 25);
			doc.text('Postal code + city', 40, 30);
			doc.text('Pays', 40, 35);
			doc.setFontSize(16);
			doc.autoTable({
				startY: 50,
				head: [['Item', 'Category', 'Quantity', 'Color', 'Price']],
				body: this.getCartItem
			});
			doc.text(
				`Total :  ${this.totalPrice} €`,
				100,
				doc.lastAutoTable.finalY + 10
			);
			doc.save('facture.pdf');
		}
	}
};
</script>

<style lang="scss" scoped>
.title-product {
	border-bottom: thin solid black;
}
.item-infos {
	border-bottom: thin solid black;
}
</style>