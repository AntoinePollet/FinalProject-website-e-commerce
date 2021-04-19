<template>
	<v-main>
		<div v-if="cart.length != 0" id="element-to-print">
			<v-container class="col-8">
				<div class="title-product d-flex">
					<h4 class="col-6 text-left pl-0">Produit</h4>
					<h4 class="col-2 text-right">Prix</h4>
					<h4 class="col-2 text-right">Quantité</h4>
					<h4 class="col-2 text-right pr-0">Total</h4>
				</div>
				<div
					v-for="(item, index) in cart"
					:key="item.id"
					class="item-infos d-flex"
				>
					<div class="col-6 text-left d-flex pl-0 pb-2">
						<div>
							<v-img
								contain
								:src="item.pictures[0]"
								:style="{ height: '100px', width: '100px' }"
							/>
						</div>
						<div class="d-flex flex-column ml-2">
							<p class="mb-0">{{ item.name }}</p>
							<v-spacer />
							<p class="remove-item" @click="removeItem(index)">Supprimer</p>
						</div>
					</div>
					<div class="col-2 text-right">{{ item.price }} €</div>
					<div class="d-flex justify-end col-2 text-right">
						<v-select
							class="col-8 pa-0 ma-0"
							:items="[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]"
							type="number"
							:value="item.quantity"
							v-model="item.quantity"
							max="10"
							min="1"
						/>
					</div>
					<div class="col-2 text-right pr-0">
						{{ item.price * item.quantity }} €
					</div>
				</div>
				<div class="d-flex justify-end">
					<div>
						<h2>
							Total : <strong>{{ totalPrice }}</strong> €
						</h2>
						<v-card-actions class="justify-center"
							><v-btn
								class="pink light-gray px-3 py-3"
								@click="$router.push({ name: 'livraison' })"
								text
								color="white"
								>Suivant</v-btn
							></v-card-actions
						>
						<v-card-actions class="justify-center">
							<v-btn class="teal" @click="pdf()" text color="white"
								>Télécharger</v-btn
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
.remove-item {
	cursor: pointer;
	text-decoration: underline;
}
</style>