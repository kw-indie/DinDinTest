package com.dindintest.data

import com.dindintest.data.model.*
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

object FoodRepo {
	private const val syntheticDelay = 500L

	// region data
	private val featuredAds = listOf(
		FeaturedAd(
			1,
			"https://i.pinimg.com/736x/f9/a0/2a/f9a02a50f05caf986b18a21e67fff419.jpg"
		),
		FeaturedAd(
			2,
			"https://i1.wp.com/www.eatthis.com/wp-content/uploads/2017/10/dark-chocolate-bar-squares.jpg?fit=1024%2C750&ssl=1"
		),
		FeaturedAd(
			3,
			"https://img.freepik.com/free-vector/hamburger-ads-design-blackboard-background-3d-illustration_317396-394.jpg?size=626&ext=jpg&ga=GA1.2.1935103904.1612742400"
		)
	)
	private val italianItems = listOf(
		Item(
			1,
			"https://www.lemonsforlulu.com/wp-content/uploads/2019/08/Veggie-Pizza-8-720x405.jpg",
			"Veggie pizza",
			"Normal veggie pizza that tastes normal",
			49.99f,
			"500gr",
			"20cm",
			listOf("veggies", "cheese"),
			listOf("vegan", "hot")
		),
		Item(
			2,
			"https://cache.dominos.com/olo/6_47_7/assets/build/market/QA/_en/images/img/products/larges/S_LGR.jpg",
			"Legendary pizza",
			"Chicken pieces with ranch and bbq sauce",
			49.99f,
			"650gr",
			"25cm",
			listOf("cheese", "chicken", "bbq sauce"),
			listOf("special", "hot")
		),
		Item(
			3,
			"https://jamiecooksitup.net/wp-content/uploads/2015/05/Margarita-Pizza-from-Jamie-Cooks-It-Up.jpg",
			"Margaretta pizza",
			"Margaretta pizza fully filled cheese",
			39.99f,
			"600gr",
			"22cm",
			listOf("cheese", "cheese", "more cheese"),
			listOf("hot")
		)
	)
	private val frenchItems = listOf(
		Item(
			4,
			"https://addapinch.com/wp-content/uploads/2014/08/ratatouille-recipe-DSC_4650-2.jpg",
			"Ratatouille",
			"I don't know what this is",
			5f,
			"200gr",
			"1 person",
			listOf("veggies", "olives"),
			listOf("vegan")
		),
		Item(
			5,
			"https://www.abeautifulplate.com/wp-content/uploads/2014/06/raspberry-souffle-1-5-480x270.jpg",
			"Souffle",
			"I don't know what this is",
			5f,
			"300gr",
			"1 person",
			listOf("ice cream"),
			listOf("bakery")
		),
		Item(
			6,
			"https://static01.nyt.com/images/2014/04/01/dining/duck-confit/duck-confit-mediumThreeByTwo440.jpg",
			"Duck confit",
			"Duck leg with salad",
			40f,
			"700gr",
			"1 person",
			listOf("full meal", "salad"),
			listOf("meat", "hot")
		)
	)
	private val drinks = listOf(
		Item(
			7,
			"https://p4.wallpaperbetter.com/wallpaper/416/254/464/5bca3e1de080f-wallpaper-preview.jpg",
			"Pepsi",
			"Ice cold pepsi",
			2.99f,
			"500ml",
			"",
			listOf("ice cubes"),
			listOf("cold")
		),
		Item(
			8,
			"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRgOfy3Ri1aysha8wbBD6qWovxUWKnS1gZ6QEY9XWJnjmDLX7bxMXInZmKZfWpZ_d5hlLs&usqp=CAU&ec=45780877",
			"Pepsi diet",
			"Ice cold pepsi diet",
			2.99f,
			"350ml",
			"",
			listOf("ice cubes"),
			listOf("cold", "diet")
		),
		Item(
			9,
			"https://cdn.tasteatlas.com/images/dishes/5291b5f2fcee42849cd84a03bff55e0a.jpg?w=600&h=450",
			"Milkshake",
			"Chocolate milkshake",
			4.99f,
			"350ml",
			"",
			listOf("choco", "strawberry", "caramel"),
			listOf("cold")
		)
	)
	private val burgers = listOf(
		Item(
			10,
			"https://wlxaj1j3fea9rr7r20slpixw-wpengine.netdna-ssl.com/wp-content/uploads/2017/10/Fast-food-combo-meal.jpg",
			"Hamburger",
			"The original hamburger",
			9.99f,
			"1 person",
			"1200 cal",
			listOf("fries", "ketchup", "mayo", "cola"),
			listOf("fastFood", "hot")
		),
		Item(
			11,
			"https://www.kfcjamaica.com/sites/default/files/2018-01/KFC_eComm_thumb_eComm_Meal%20Deal_0.jpg",
			"Chicken legs",
			"Best chicken legs ever",
			14.99f,
			"1 person",
			"1500 cal",
			listOf("fries", "ketchup", "special sauce", "cola"),
			listOf("fastFood", "hot")
		),
		Item(
			12,
			"https://www.kfcjamaica.com/sites/default/files/2019-04/Wings%208.jpg",
			"Chicken wings",
			"Wings to help you stick more to your chair instead of flying",
			9.99f,
			"kids meal",
			"800 cal",
			listOf("saucy sauce", "fries", "milk"),
			listOf("fastFood", "hot")
		)
	)
	private val allItems = italianItems + frenchItems + drinks + burgers
	private val menus = listOf(
		Menu(1, "Italian", italianItems),
		Menu(2, "French", frenchItems),
		Menu(3, "Drinks", drinks),
		Menu(4, "Burgers", burgers)
	)
	// endregion

	private val cartItemMap = mutableMapOf<Long, Int>()
	private var cartNote = ""

	fun getFeaturedAds(): Observable<List<FeaturedAd>> = Observable.fromCallable {
		Thread.sleep(syntheticDelay)
		featuredAds
	}.subscribeOn(Schedulers.io())

	fun getMenus(): Observable<List<Menu>> = Observable.fromCallable {
		Thread.sleep(syntheticDelay)
		menus
	}.subscribeOn(Schedulers.io())

	fun addToCartOrUpdate(itemId: Long, qty: Int = 1): Observable<Int> = Observable.fromCallable {
		Thread.sleep(syntheticDelay)
		cartItemMap.merge(itemId, qty) { o, n -> o + n }
		cartItemMap.size
	}.subscribeOn(Schedulers.io())

	fun getCart(): Observable<Cart> = Observable.fromCallable {
		Thread.sleep(syntheticDelay)
		Cart(getCartItems(), cartNote)
	}.subscribeOn(Schedulers.io())

	private fun getItem(itemId: Long) = allItems.first { it.id == itemId }

	private fun getCartItems() = cartItemMap.map { (itemId, qty) ->
		CartItem(getItem(itemId), qty)
	}

	fun removeFromCart(itemId: Long): Observable<Cart> = Observable.fromCallable {
		Thread.sleep(syntheticDelay)
		cartItemMap.remove(itemId)
		Cart(getCartItems(), cartNote)
	}.subscribeOn(Schedulers.io())

	fun setCartNote(note: String): Observable<String> = Observable.fromCallable {
		Thread.sleep(syntheticDelay)
		cartNote = note
		cartNote
	}.subscribeOn(Schedulers.io())

	fun checkout(): Observable<Unit> = Observable.fromCallable {
		Thread.sleep(syntheticDelay)
		cartItemMap.clear()
		cartNote = ""
	}.subscribeOn(Schedulers.io())
}