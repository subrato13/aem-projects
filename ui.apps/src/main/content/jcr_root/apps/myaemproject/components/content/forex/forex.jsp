<%@include file="/libs/foundation/global.jsp" %>
<div class="currency">

    <h2>Forex Rates</h2>

    <div class="forex-rate">
        <p> Base :<span id="base">EUR</span></p> <div class="date"><span >Date:</span><span id="date"></span></div>
		<p>	
        GBP : <span id="gbp"></span><br>
        USD : <span id="usd"></span><br>
        INR : <span id="inr"></span><br>
        </p>

    </div>
</div>    
<script>
$( document ).ready(function() {
    forexRate();
});

</script>
