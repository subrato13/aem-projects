function forexRate() {

$.ajax({
    url: '/bin/myForexServlet',
      method: 'GET',  
      success: function(data) {
  	  populateForex(JSON.parse(data));
    },
    error: function(data) {

    },
    complete: function() {
      // Schedule the next request when the current one's complete
      setTimeout(forexRate, 5000);
    }
  });
}

function populateForex(data){
   
    $('.forex #date').html(data.date);
    $('.forex #gbp').html(data.rates.GBP);
    $('.forex #usd').html(data.rates.USD);
    $('.forex #inr').html(data.rates.INR);

}